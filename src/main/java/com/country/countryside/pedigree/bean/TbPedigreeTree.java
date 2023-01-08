package com.country.countryside.pedigree.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 族谱树
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Data
public class TbPedigreeTree implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 村庄id
     */
    private String countryId;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 关联族谱id
     */
    private String pedigreeId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 配偶id
     */
    private String wifeId;

    /**
     * 是否删除标识 0：否 1：是
     */
    private Integer isDelete;

    /**
     * 层级从0开始
     */
    private Integer layer;
}
