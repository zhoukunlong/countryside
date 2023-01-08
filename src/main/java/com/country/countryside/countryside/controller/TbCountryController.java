package com.country.countryside.countryside.controller;

import com.country.countryside.common.BaseResult;
import com.country.countryside.countryside.service.TbCountryService;
import com.country.countryside.countryside.vo.CountryInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 村庄前端控制类
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
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
    @RequestMapping(value = "/addCountry.do", method = RequestMethod.POST)
    public BaseResult addCountry(HttpServletRequest request, @Validated @RequestBody CountryInVo inVo){
        tbCountryService.addCountry(inVo);
        return BaseResult.success(null);
    }

    /**
     * 用户申请加入村庄
     * @param request
     * @param userId
     * @param countryId
     * @return
     */
    @RequestMapping(value = "/joinCountry.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult joinCountry(HttpServletRequest request, @NotBlank(message = "用户id不能为空") String userId,
                                  @NotBlank(message = "村庄id不能为空") String countryId){
        tbCountryService.joinCountry(userId,countryId);
        return BaseResult.success(null);
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
}
