package com.country.countryside.pedigree.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 族谱信息树入参
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@ApiModel(value = "关系树节点入参", description = "关系树节点入参")
@Data
public class PedigreeTreeInVo implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id", required = false,
            example = "123123", notes = "主键id，新增时不用传递，修改时必须传递")
    private String id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true, example = "123123")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    /**
     * 村庄id
     */
    @ApiModelProperty(value = "村庄id", required = true, example = "123123")
    @NotBlank(message = "村庄id不能为空")
    private String countryId;

    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id", required = true, example = "123123")
    @NotBlank(message = "父节点不能为空")
    private String parentId;

}
