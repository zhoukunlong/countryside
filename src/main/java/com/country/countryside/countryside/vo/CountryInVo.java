package com.country.countryside.countryside.vo;

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
public class CountryInVo {

    /**
     * 村庄名称
     */
    @NotBlank(message = "村庄名称不能为空")
    private String countryName;

    /**
     * 分组id
     */
    @NotBlank(message = "所属镇不能为空")
    private String groupId;

    /**
     * 村庄描述
     */
    private String countryDesc;

    /**
     * 村庄年龄
     */
    private Integer countryAge;

    /**
     * 过期时间
     */
    @NotBlank(message = "过期时间不能为空")
    private String expireTime;

}
