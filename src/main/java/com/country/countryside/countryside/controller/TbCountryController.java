package com.country.countryside.countryside.controller;

import com.country.countryside.common.BaseResult;
import com.country.countryside.countryside.service.TbCountryService;
import com.country.countryside.countryside.vo.CountryInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
}
