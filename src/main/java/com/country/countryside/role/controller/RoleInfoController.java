package com.country.countryside.role.controller;

import com.country.countryside.common.BaseResult;
import com.country.countryside.role.service.TbRoleInfoService;
import com.country.countryside.role.vo.RoleInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色管理模块
 * @author zhoukunlong
 * @date 2023/01/16
 * @since
 * @see
 */
@RestController
@RequestMapping(value = "/role")
public class RoleInfoController {

    @Autowired
    private TbRoleInfoService tbRoleInfoService;

    /**
     * 添加角色信息
     * @param request
     * @param inVo
     * @return
     */
    @RequestMapping(value = "/addRole.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addRole(HttpServletRequest request, @Validated @RequestBody RoleInVo inVo){
        return BaseResult.success(tbRoleInfoService.addRole(inVo));
    }
}
