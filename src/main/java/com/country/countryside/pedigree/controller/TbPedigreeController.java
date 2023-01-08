package com.country.countryside.pedigree.controller;


import com.country.countryside.common.BaseResult;
import com.country.countryside.pedigree.service.TbPedigreeService;
import com.country.countryside.pedigree.vo.PedigreeInVo;
import com.country.countryside.pedigree.vo.PedigreeTreeInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 族谱信息前端控制类
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@RestController
@RequestMapping(value = "/pedigree")
public class TbPedigreeController {

    @Autowired
    private TbPedigreeService tbPedigreeService;

    /**
     * 添加族谱信息
     * @param request
     * @param inVo
     * @return
     */
    @RequestMapping(value = "/addPedigree.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addPedigree(HttpServletRequest request, @Validated @RequestBody PedigreeInVo inVo){
        tbPedigreeService.addPedigree(inVo);
        return BaseResult.success(null);
    }

    /**
     * 添加族谱信息树节点
     * @param request
     * @param inVo
     * @return
     */
    @RequestMapping(value = "/addPedigreeTree.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addPedigreeTree(HttpServletRequest request, @Validated @RequestBody PedigreeTreeInVo inVo){
        tbPedigreeService.addPedigreeTree(inVo);
        return BaseResult.success(null);
    }
}
