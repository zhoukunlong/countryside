package com.country.countryside.pedigree.controller;


import com.country.countryside.common.BaseResult;
import com.country.countryside.common.CommonUtils;
import com.country.countryside.pedigree.service.TbPedigreeService;
import com.country.countryside.pedigree.vo.PedigreeInVo;
import com.country.countryside.pedigree.vo.PedigreeTreeInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * 族谱信息前端控制类
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Api(tags = "关系树管理模块")
@RestController
@RequestMapping(value = "/pedigree")
public class TbPedigreeController {

    @Autowired
    private TbPedigreeService tbPedigreeService;

    /**
     * 申请创建族谱
     * @param request
     * @param name
     * @return
     */
    @ApiOperation(value = "申请创建族谱", notes = "申请创建族谱")
    @RequestMapping(value = "/applyPedigree.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult applyPedigree(HttpServletRequest request,
                                    @NotBlank(message = "族谱名称不能为空") String name,
                                    @NotBlank(message = "村庄id不能为空") String countryId){
        String userId = CommonUtils.getUserId(request);
        tbPedigreeService.applyPedigree(userId,name,countryId);
        return BaseResult.success(null);
    }

    /**
     * 添加族谱信息
     * @param request
     * @param inVo
     * @return
     */
    @ApiOperation(value = "添加族谱接口", notes = "添加族谱信息")
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
    @ApiOperation(value = "添加关系树节点接口", notes = "添加关系树节点信息")
    @RequestMapping(value = "/addPedigreeTree.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addPedigreeTree(HttpServletRequest request, @Validated @RequestBody PedigreeTreeInVo inVo){
        tbPedigreeService.addPedigreeTree(inVo);
        return BaseResult.success(null);
    }

    /**
     * 继承父节点
     * @param request
     * @param startUser
     * @param targetUser
     * @return
     */
    @ApiOperation(value = "继承接口", notes = "设置父节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startUser", value = "子用户id", required = true,
                    dataType = "String", example = "123123"),
            @ApiImplicitParam(name = "targetUser", value = "父用户id", required = true,
                    dataType = "String", example = "123123")
    })
    @RequestMapping(value = "/extend.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult extend(HttpServletRequest request,
                             @NotBlank(message = "startUser不能为空") String startUser,
                             @NotBlank(message = "targetUser不能为空") String targetUser){
        tbPedigreeService.extend(startUser, targetUser);
        return BaseResult.success(null);
    }

    /**
     * 修改族谱开始源头
     * @param id
     * @param startIndex
     * @return
     */
    @ApiOperation(value = "修改族谱起始点接口", notes = "修改族谱起始点，设置开始节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "族谱id", required = true,
                    dataType = "String", example = "123123", paramType = "query"),
            @ApiImplicitParam(name = "startIndex", value = "关系树节点id",
                    required = true, dataType = "String", example = "123123", paramType = "query")
    })
    @RequestMapping(value = "/updateStartIndex.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult updateStartIndex(@NotBlank(message = "族谱id不能为空") String id,
                                       @NotBlank(message = "开始节点不能为空") String startIndex){
        tbPedigreeService.updateStartIndex(id, startIndex);
        return BaseResult.success(null);
    }

    /**
     * 根据父节点id查询树节点
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "根据父节点ID查询子树接口", notes = "根据父节点ID查询子树信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父节点id", required = true,
                    dataType = "String", paramType = "query",example = "123123")
    })
    @RequestMapping(value = "/findByParentId.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult findByParentId(HttpServletRequest request,
                                     @NotBlank(message = "id不能为空") String id){
        return BaseResult.success(tbPedigreeService.findByParentId(id));
    }

    /**
     * 根据id查询节点信息
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询节点信息接口", notes = "根据id查询节点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true,
                    dataType = "String", paramType = "query",example = "123123")
    })
    @RequestMapping(value = "/findById.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult findById(HttpServletRequest request,
                               @NotBlank(message = "id不能为空") String id){
        return BaseResult.success(tbPedigreeService.findById(id));
    }
}
