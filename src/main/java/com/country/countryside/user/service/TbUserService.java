package com.country.countryside.user.service;

import com.country.countryside.user.bean.TbUser;
import com.country.countryside.user.vo.UserInVo;

/**
 * 用户模块
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
public interface TbUserService {

    /**
     * 添加用户信息
     * @param inVo
     */
    void addUser(UserInVo inVo, Integer flag);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 修改用户信息
     * @param inVo
     */
    void updateUser(UserInVo inVo);

    /**
     * 用户登入
     * @param account
     * @param password
     * @return
     */
    TbUser login(String account, String password);
}
