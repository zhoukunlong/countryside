package com.country.countryside.pedigree.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 族谱信息树入参
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Data
public class PedigreeTreeInVo implements Serializable {

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
     * 配偶id
     */
    private String wifeId;
}
