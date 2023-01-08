package com.country.countryside.pedigree.vo;

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
@Data
public class PedigreeInVo implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 族谱名称
     */
    @NotBlank(message = "族谱名称不能为空")
    private String pedigreeName;

    /**
     * 村庄Id
     */
    @NotBlank(message = "村庄id不能为空")
    private String countryId;

    /**
     * 族谱描述
     */
    private String pedigreeDesc;

    /**
     * 族谱图片
     */
    private String pedigreeImg;
}
