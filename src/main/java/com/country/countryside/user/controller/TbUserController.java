package com.country.countryside.user.controller;

import com.country.countryside.common.BaseResult;
import com.country.countryside.common.CommonConstants;
import com.country.countryside.config.enums.ErrorCodeEnum;
import com.country.countryside.context.JwtPayload;
import com.country.countryside.exception.DescribeException;
import com.country.countryside.user.bean.TbUser;
import com.country.countryside.user.service.TbUserService;
import com.country.countryside.user.vo.LoginInVo;
import com.country.countryside.user.vo.UserInVo;
import com.country.countryside.utils.JwtUtils;
import com.country.countryside.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户模块
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Api(tags = "用户管理模块")
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
    @ApiOperation(value = "用户注册接口", notes = "注册用户信息")
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
    @ApiOperation(value = "添加用户接口", notes = "用于管理员添加用户")
    @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addUser(HttpServletRequest request, @Validated @RequestBody UserInVo inVo){
        tbUserService.addUser(inVo, CommonConstants.UserResource.ADD);
        return BaseResult.success(null);
    }

    /**
     * 用户登入
     * @param request
     * @return
     */
    @ApiOperation(value = "用户登入接口", notes = "用户登入")
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(HttpServletRequest request, HttpServletResponse response,
                            @Validated @RequestBody LoginInVo inVo){

        String password = Md5Utils.md5(inVo.getPassword());
        TbUser tbUser = tbUserService.login(inVo.getAccount(), password);
        if(tbUser == null){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30004.getCode(),ErrorCodeEnum.ERROR_0xbdc30004.getTips());
        }
        //生成token,将token放置在cookie中
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setUserId(tbUser.getId());
        jwtPayload.setCountryId(tbUser.getCountryId());
        jwtPayload.setUserName(tbUser.getUserName());
        String token = JwtUtils.addAuth(jwtPayload);

        /**
         * 将token放置在cookie中
         */
        Boolean flag = true;
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            flag = true;
        }else {
            for (Cookie cookie : cookies) {
                if ("Authorization".equalsIgnoreCase(cookie.getName())) {
                    cookie.setValue(token);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    flag = false;
                }
            }
        }
        if(flag){
            Cookie _cookie = new Cookie("Authorization",token);
            _cookie.setPath("/");
            response.addCookie(_cookie);
        }
        return BaseResult.success(token);
    }

    /**
     * 退出登入
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "退出登入接口", notes = "退出登入")
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult logout(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if("Authorization".equalsIgnoreCase(cookie.getName())){
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return BaseResult.success(null);
    }

    /**
     * 修改用户信息
     * @param request
     * @param inVo
     * @return
     */
    public BaseResult updateUserInfo(HttpServletRequest request, @Validated @RequestBody UserInVo inVo){
        return BaseResult.success(null);
    }
}
