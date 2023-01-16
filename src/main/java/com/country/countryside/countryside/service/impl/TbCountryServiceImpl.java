package com.country.countryside.countryside.service.impl;

import com.country.countryside.common.CommonConstants;
import com.country.countryside.config.enums.ErrorCodeEnum;
import com.country.countryside.countryside.bean.TbCountry;
import com.country.countryside.countryside.bean.TbProcess;
import com.country.countryside.countryside.mapper.TbCountryMapper;
import com.country.countryside.countryside.mapper.TbProcessMapper;
import com.country.countryside.countryside.service.TbCountryService;
import com.country.countryside.countryside.vo.CountryInVo;
import com.country.countryside.exception.DescribeException;
import com.country.countryside.user.bean.TbUser;
import com.country.countryside.user.mapper.TbUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

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

    /**
     * 添加村庄
     * @param inVo
     */
    @Transactional
    @Override
    public void addCountry(CountryInVo inVo) {
        try {
            TbCountry tbCountry = new TbCountry();
            BeanUtils.copyProperties(inVo,tbCountry);
            tbCountry.setCreateTime(CommonConstants.format.format(new Date()));
            tbCountry.setUpdateTime(CommonConstants.format.format(new Date()));
            tbCountry.setIsDelete(CommonConstants.Delete.NO);
            tbCountryMapper.addCountry(tbCountry);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 申请加入村庄
     * @param userId
     * @param countryId
     */
    @Transactional
    @Override
    public void joinCountry(String userId, String countryId) {
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
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc20001.getCode(),ErrorCodeEnum.ERROR_0xbdc20001.getTips());
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
        tbProcess.setProcessTitle(CommonConstants.CONTENT);
        tbProcess.setProcessContent(CommonConstants.CONTENT);
        //websocket推送消息给村长
        tbProcessMapper.addProcess(tbProcess);
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
