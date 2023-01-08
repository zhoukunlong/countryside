package com.country.countryside.user.service.impl;

import com.country.countryside.common.CommonConstants;
import com.country.countryside.user.bean.TbUser;
import com.country.countryside.user.mapper.TbUserMapper;
import com.country.countryside.user.service.TbUserService;
import com.country.countryside.user.vo.UserInVo;
import com.country.countryside.utils.Md5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户模块
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper tbUserMapper;

    /**
     * 添加用户信息
     * @param inVo
     * @param flag
     */
    @Override
    public void addUser(UserInVo inVo, Integer flag) {
        TbUser tbUser = new TbUser();
        BeanUtils.copyProperties(inVo,tbUser);
        tbUser.setIsDelete(CommonConstants.Delete.NO);
        tbUser.setCreateTime(CommonConstants.format.format(new Date()));
        tbUser.setUpdateTime(CommonConstants.format.format(new Date()));
        tbUser.setUserResource(flag);
        if(flag == CommonConstants.UserResource.REGIST){
            tbUser.setPassword(Md5Utils.md5(inVo.getPassword()));
        }else {
            tbUser.setPassword(Md5Utils.md5(CommonConstants.DEFAULT_PASSWORD));
        }
        tbUserMapper.addUser(tbUser);
    }

    /**
     * 删除用户信息
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {

    }

    /**
     * 修改用户信息
     * @param inVo
     */
    @Override
    public void updateUser(UserInVo inVo) {

    }
}
