package com.country.countryside.countryside.controller;

import com.country.countryside.common.BaseResult;
import com.country.countryside.common.CommonUtils;
import com.country.countryside.config.enums.ErrorCodeEnum;
import com.country.countryside.countryside.service.TbCountryService;
import com.country.countryside.countryside.vo.CountryInVo;
import com.country.countryside.exception.DescribeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 村庄前端控制类
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Api(tags = "村庄管理模块")
@RestController
@RequestMapping(value = "/country")
public class TbCountryController {

    @Autowired
    private TbCountryService tbCountryService;

    /**
     * 添加村庄
     * @param request
     * @param inVo
     * @return
     */
    @ApiOperation(value = "村庄添加接口", notes = "添加村庄信息")
    @RequestMapping(value = "/addCountry.do", method = RequestMethod.POST)
    public BaseResult addCountry(HttpServletRequest request, @Validated @RequestBody CountryInVo inVo){
        tbCountryService.addCountry(request,inVo);
        return BaseResult.success(null);
    }

    /**
     * 用户申请加入村庄
     * @param request
     * @param countryId
     * @return
     */
    @ApiOperation(value = "申请加入村庄接口", notes = "用户可以根据自身需求申请加入某个村庄，等待该村管理员审批")
    @RequestMapping(value = "/joinCountry.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult joinCountry(HttpServletRequest request, @NotBlank(message = "村庄id不能为空") String countryId){
        String userId = CommonUtils.getUserId(request) == null ? "40bbd112-b43e-4591-8aff-faf98bffea06" : CommonUtils.getUserId(request);
        if(StringUtils.isBlank(userId)){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30002.getCode(),ErrorCodeEnum.ERROR_0xbdc30002.getTips());
        }
        return BaseResult.success(tbCountryService.joinCountry(userId,countryId));
    }

    /**
     * 审批工单
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/approve.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult approve(HttpServletRequest request, @NotBlank(message = "工单id不能为空") String id,
                              @NotNull Integer status){
        tbCountryService.approve(id, status);
        return BaseResult.success(null);
    }

    /**
     * 申请退出村庄,用户退出村庄需要通过申请才能退出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/applyExitCountry.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult applyExitCountry(HttpServletRequest request, HttpServletResponse response){
        return BaseResult.success(null);
    }

    /**
     * 管理员从村庄中移除用户
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUser.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult deleteUser(HttpServletRequest request, @NotBlank(message = "用户id不能为空") String userId){

        return BaseResult.success(null);
    }


}
