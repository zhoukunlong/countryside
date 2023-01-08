package com.country.countryside.region.controller;

import com.country.countryside.common.BaseResult;
import com.country.countryside.region.service.TbRegionTreeService;
import com.country.countryside.region.vo.RegionInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 地市级联前端控制类
 */
@RestController
@RequestMapping(value = "/region")
public class TbRegionController {

    @Autowired
    private TbRegionTreeService tbRegionTreeService;

    /**
     * 添加地市级联信息
     * @param request
     * @param inVo
     * @return
     */
    @RequestMapping(value = "/addRegion.do", method = RequestMethod.POST)
    public BaseResult addRegion(HttpServletRequest request, @Validated @RequestBody RegionInVo inVo){
        tbRegionTreeService.addRegion(inVo);
        return BaseResult.success(null);
    }

    /**
     * 获取地市信息树
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/findRegionTrees.do", method = RequestMethod.GET)
    public BaseResult findRegionTrees(HttpServletRequest request, HttpServletResponse response){
        return BaseResult.success(tbRegionTreeService.findRegionTrees());
    }
}
