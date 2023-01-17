package com.country.countryside.countryside.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.country.countryside.common.CommonConstants;
import com.country.countryside.common.CommonUtils;
import com.country.countryside.config.enums.ErrorCodeEnum;
import com.country.countryside.countryside.bean.TbCountry;
import com.country.countryside.countryside.bean.TbProcess;
import com.country.countryside.countryside.mapper.TbCountryMapper;
import com.country.countryside.countryside.mapper.TbProcessMapper;
import com.country.countryside.countryside.service.TbCountryService;
import com.country.countryside.countryside.vo.CountryInVo;
import com.country.countryside.exception.DescribeException;
import com.country.countryside.role.bean.TbUserRole;
import com.country.countryside.role.service.TbRoleInfoService;
import com.country.countryside.role.vo.UserRoleInVo;
import com.country.countryside.user.bean.TbUser;
import com.country.countryside.user.mapper.TbUserMapper;
import com.country.countryside.utils.WebSocketUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.Date;
import java.util.List;

/**
 * 村庄业务操作类
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Service
public class TbCountryServiceImpl implements TbCountryService {

    @Resource
    private TbCountryMapper tbCountryMapper;
    @Resource
    private TbProcessMapper tbProcessMapper;
    @Resource
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbRoleInfoService tbRoleInfoService;

    /**
     * 添加村庄
     * @param inVo
     */
    @Transactional
    @Override
    public void addCountry(HttpServletRequest request, CountryInVo inVo) {

        String userId = CommonUtils.getUserId(request);
        if(StringUtils.isEmpty(userId)){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30002.getCode(),ErrorCodeEnum.ERROR_0xbdc30002.getTips());
        }
        /**
         * 首先判断用户是否再其他村庄
         */
        TbUser tbUser = tbUserMapper.findById(userId);
        if(!StringUtils.isEmpty(tbUser.getCountryId())){
            TbCountry tbCountry = tbCountryMapper.findById(tbUser.getCountryId());
            if(tbCountry != null){
                throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30001.getCode(),ErrorCodeEnum.ERROR_0xbdc30001.getTips());
            }
        }
        /**
         * 添加村庄信息
         */
        TbCountry tbCountry = new TbCountry();
        BeanUtils.copyProperties(inVo,tbCountry);
        tbCountry.setCreateTime(CommonConstants.format.format(new Date()));
        tbCountry.setUpdateTime(CommonConstants.format.format(new Date()));
        tbCountry.setIsDelete(CommonConstants.Delete.NO);
        tbCountryMapper.addCountry(tbCountry);
        /**
         * 给申请用户赋超级管理员权限
         */
        UserRoleInVo _inVo = new UserRoleInVo();
        _inVo.setUserId(userId);
        _inVo.setCountryId(tbCountry.getId());
        _inVo.setRelatName(CommonConstants.DEFAULT_ROLE_NAME);
        _inVo.setRoleId(CommonConstants.ROLE.administrator);
        tbRoleInfoService.addUserRole(_inVo);
    }

    /**
     * 申请加入村庄
     * @param userId
     * @param countryId
     */
    @Transactional
    @Override
    public String joinCountry(String userId, String countryId) {
        /**
         * 判断用户是否已加入村庄
         */
        TbUser tbUser = tbUserMapper.findById(userId);
        if(!StringUtils.isEmpty(tbUser.getCountryId())){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30001.getCode(),ErrorCodeEnum.ERROR_0xbdc30001.getTips());
        }
        /**
         * 判断申请加入的村庄是否存在
         */
        TbCountry tbCountry = tbCountryMapper.findById(countryId);
        if(tbCountry == null){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30003.getCode(),ErrorCodeEnum.ERROR_0xbdc30003.getTips());
        }
        /**
         * 判断用户是否有在途工单
         */
        List<TbProcess> tbProcesss = tbProcessMapper.findByUserId(userId);
        if(tbProcesss != null && tbProcesss.size() > 0){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc50001.getCode(),ErrorCodeEnum.ERROR_0xbdc50001.getTips());
        }
        /**
         * 如果存在，计入申请工单，等待村长审批
         */
        TbProcess tbProcess = new TbProcess();
        tbProcess.setStatus(CommonConstants.ProcessStatus.PROCESSING);
        tbProcess.setIsDelete(CommonConstants.Delete.NO);
        tbProcess.setCountryId(countryId);
        tbProcess.setUserId(userId);
        tbProcess.setCreateTime(CommonConstants.format.format(new Date()));
        tbProcess.setUpdateTime(CommonConstants.format.format(new Date()));
        tbProcess.setApproveRoleId(CommonConstants.ROLE.administrator);
        tbProcess.setProcessTitle(CommonConstants.CONTENT);
        tbProcess.setProcessContent(CommonConstants.CONTENT);
        tbProcessMapper.addProcess(tbProcess);
        //websocket推送消息给村庄管理员
        List<TbUserRole> userRoleList = tbRoleInfoService.findByRoleId(CommonConstants.ROLE.administrator, countryId);
        if(userRoleList != null && userRoleList.size() > 0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("applyUser",userId);
            jsonObject.put("content", CommonConstants.CONTENT);
            jsonObject.put("countryName", tbCountry.getCountryName());
            jsonObject.put("processId",tbProcess.getId());
            List<Session> sessions = WebSocketUtils.getSession(userId);
            sessions.forEach((session) -> {
                WebSocketUtils.sendMessage(jsonObject.toJSONString(),session);
            });
        }
        return tbProcess.getId();
    }

    /**
     * 审批工单
     * @param id
     */
    @Transactional
    @Override
    public void approve(String id, Integer status) {
        TbProcess tbProcess = tbProcessMapper.findById(id);
        if(tbProcess == null){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc20002.getCode(),ErrorCodeEnum.ERROR_0xbdc20002.getTips());
        }
        tbProcessMapper.updateProcess(id,status);
        //如果审批通过则修改用户信息中的村庄字段为申请加入的村庄
        if(status == CommonConstants.ProcessStatus.PASS){
            TbUser tbUser = new TbUser();
            tbUser.setId(tbProcess.getUserId());
            tbUser.setCountryId(tbProcess.getCountryId());
            tbUserMapper.updateUser(tbUser);
        }
    }
}
