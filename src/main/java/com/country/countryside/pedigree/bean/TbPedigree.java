package com.country.countryside.pedigree.bean;

import com.country.countryside.config.id.Id;
import com.country.countryside.config.id.UUIDGenerator;
import lombok.Data;

import java.io.Serializable;

/**
 * 族谱信息表
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Data
public class TbPedigree implements Serializable {

    /**
     * 主键
     */
    @Id(strategy = UUIDGenerator.class)
    private String id;

    /**
     * 族谱名称
     */
    private String pedigreeName;

    /**
     * 村庄Id
     */
    private String countryId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 是否删除标识
     */
    private Integer isDelete;

    /**
     * 族谱描述
     */
    private String pedigreeDesc;

    /**
     * 族谱图片
     */
    private String pedigreeImg;

    /**
     * 关系树开始节点
     */
    private String startIndex;

}
