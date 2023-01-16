package com.country.countryside.countryside.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 村庄
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Data
@ApiModel(value = "村庄入参对象", description = "村庄入参对象")
public class CountryInVo {

    /**
     * 村庄名称
     */
    @ApiModelProperty(value = "村庄名称", required = true, example = "")
    @NotBlank(message = "村庄名称不能为空")
    private String countryName;

    /**
     * 分组id
     */
    @ApiModelProperty(value = "村庄所属分组id", required = true, example = "")
    @NotBlank(message = "所属镇不能为空")
    private String groupId;

    /**
     * 村庄描述
     */
    @ApiModelProperty(value = "村庄的详细描述", required = false, example = "")
    private String countryDesc;

    /**
     * 村庄年龄
     */
    @ApiModelProperty(value = "村庄年龄", required = false, example = "")
    private Integer countryAge;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间", required = true, example = "")
    @NotBlank(message = "过期时间不能为空")
    private String expireTime;

}
