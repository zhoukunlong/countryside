package com.country.countryside.region.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 地市级联树
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Data
public class RegionTreesOutVo implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 地市名称
     */
    private String regionName;

    /**
     * 层级 从1开始
     */
    private Integer layer;

    /**
     * 是否是叶子节点  0：否  1：是
     */
    private Integer isLeaf;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 地市编码
     */
    private String regionCode;

    /**
     * 村庄数量
     */
    private Integer count;

    /**
     * 子节点
     */
    private List<RegionTreesOutVo> childs;
}
