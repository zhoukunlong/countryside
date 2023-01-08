package com.country.countryside.region.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 入参
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Data
public class RegionInVo implements Serializable {

    /**
     * 地市名称
     */
    @NotBlank(message = "地市名称不能为空")
    private String regionName;

    /**
     * 父节点id
     */
    @NotBlank(message = "父节点不能为空")
    private String parentId;

    /**
     * 地市编码
     */
    private String regionCode;
}
