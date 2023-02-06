package com.country.countryside.pedigree.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 族谱信息入参
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@ApiModel(value = "族谱信息入参模型", description = "族谱信息入参模型")
@Data
public class PedigreeInVo implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id", required = false, example = "123123")
    private String id;

    /**
     * 族谱名称
     */
    @ApiModelProperty(value = "族谱名称", required = true, example = "XXX族谱")
    @NotBlank(message = "族谱名称不能为空")
    private String pedigreeName;

    /**
     * 村庄Id
     */
    @ApiModelProperty(value = "村庄id", required = true, example = "123123")
    @NotBlank(message = "村庄id不能为空")
    private String countryId;

    /**
     * 族谱描述
     */
    @ApiModelProperty(value = "族谱描述", required = false, example = "xxx族谱")
    private String pedigreeDesc;

    /**
     * 族谱图片
     */
    @ApiModelProperty(value = "族谱封面图片", required = false, example = "http://localhost/aaa.jpg")
    private String pedigreeImg;

    /**
     * 关系树源头下标
     */
    @ApiModelProperty(value = "族谱开始节点，关系树id", required = false, example = "123123")
    @NotBlank(message = "开始节点不能为空")
    private String startIndex;
}
