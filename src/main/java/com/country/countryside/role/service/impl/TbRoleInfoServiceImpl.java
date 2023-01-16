package com.country.countryside.role.service.impl;

import com.country.countryside.common.CommonConstants;
import com.country.countryside.role.bean.TbRole;
import com.country.countryside.role.bean.TbUserRole;
import com.country.countryside.role.mapper.TbRoleMapper;
import com.country.countryside.role.mapper.TbUserRoleMapper;
import com.country.countryside.role.service.TbRoleInfoService;
import com.country.countryside.role.vo.RoleInVo;
import com.country.countryside.role.vo.UserRoleInVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 角色管理模块
 * @author zhoukunlong
 * @date 2023/01/16
 * @since
 * @see
 */
@Service
public class TbRoleInfoServiceImpl implements TbRoleInfoService {

    @Resource
    private TbRoleMapper tbRoleMapper;
    @Resource
    private TbUserRoleMapper tbUserRoleMapper;

    /**
     * 根据角色id以及村庄id查询用户角色关联表
     * @param roleId
     * @param countryId
     * @return
     */
    @Override
    public List<TbUserRole> findByRoleId(String roleId, String countryId) {
        return tbUserRoleMapper.findByRoleId(roleId,countryId);
    }

    /**
     * 添加角色信息
     * @param inVo
     * @return
     */
    @Transactional
    @Override
    public String addRole(RoleInVo inVo) {
        TbRole tbRole = new TbRole();
        BeanUtils.copyProperties(inVo, tbRole);
        tbRole.setCreateTime(CommonConstants.format.format(new Date()));
        tbRole.setUpdateTime(CommonConstants.format.format(new Date()));
        tbRole.setIsDelete(CommonConstants.Delete.NO);
        tbRoleMapper.addRole(tbRole);
        return tbRole.getId();
    }

    /**
     * 添加用户角色关联信息
     * @param inVo
     * @return
     */
    @Transactional
    @Override
    public String addUserRole(UserRoleInVo inVo) {
        TbUserRole tbUserRole = new TbUserRole();
        BeanUtils.copyProperties(inVo,tbUserRole);
        tbUserRole.setCreateTime(CommonConstants.format.format(new Date()));
        tbUserRole.setUpdateTime(CommonConstants.format.format(new Date()));
        tbUserRole.setIsDelete(CommonConstants.Delete.NO);
        tbUserRoleMapper.addUserRole(tbUserRole);
        return tbUserRole.getId();
    }
}
