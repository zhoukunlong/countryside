package com.country.countryside.role.service;

import com.country.countryside.role.bean.TbUserRole;
import com.country.countryside.role.vo.RoleInVo;
import com.country.countryside.role.vo.UserRoleInVo;

import java.util.List;

/**
 * 角色管理模块
 * @author zhoukunlong
 * @date 2023/01/16
 * @since
 * @see
 */
public interface TbRoleInfoService {

    /**
     * 根据角色id以及村庄id查询用户角色关联信息
     * @param roleId
     * @param countryId
     * @return
     */
    List<TbUserRole> findByRoleId(String roleId, String countryId);

    /**
     * 添加角色信息
     * @param inVo
     * @return
     */
    String addRole(RoleInVo inVo);

    /**
     * 添加用户角色关联信息
     * @param inVo
     * @return
     */
    String addUserRole(UserRoleInVo inVo);
}
