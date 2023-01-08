package com.country.countryside.user.controller;

import com.country.countryside.common.BaseResult;
import com.country.countryside.common.CommonConstants;
import com.country.countryside.user.service.TbUserService;
import com.country.countryside.user.vo.UserInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@RestController
@RequestMapping(value = "/user")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 注册用户
     * @param request
     * @param inVo
     * @return
     */
    @RequestMapping(value = "/regist.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult regist(HttpServletRequest request, @Validated @RequestBody UserInVo inVo){
        tbUserService.addUser(inVo, CommonConstants.UserResource.REGIST);
        return BaseResult.success(null);
    }

    /**
     * 管理员添加用户
     * @param request
     * @param inVo
     * @return
     */
    @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addUser(HttpServletRequest request, @Validated @RequestBody UserInVo inVo){
        tbUserService.addUser(inVo, CommonConstants.UserResource.ADD);
        return BaseResult.success(null);
    }
}
