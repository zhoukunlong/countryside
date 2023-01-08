package com.country.countryside.region.bean;

import com.country.countryside.config.id.Id;
import com.country.countryside.config.id.UUIDGenerator;
import lombok.Data;

import java.io.Serializable;

/**
 * 地市级联实体
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Data
public class TbRegionTree implements Serializable {

    /**
     * 主键id
     */
    @Id(strategy = UUIDGenerator.class)
    private String id;

    /**
     * 地市名称
     */
    private String regionName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 是否删除 0：否  1：是
     */
    private Integer isDelete;

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
}
