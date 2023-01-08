package com.country.countryside.countryside.bean;

import com.country.countryside.config.id.Id;
import com.country.countryside.config.id.UUIDGenerator;
import lombok.Data;

import java.io.Serializable;

/**
 * 村庄信息实体
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Data
public class TbCountry implements Serializable {

    /**
     * 主键id
     */
    @Id(strategy = UUIDGenerator.class)
    private String id;

    /**
     * 村庄名称
     */
    private String countryName;

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
     * 分组id
     */
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
    private String expireTime;

    /**
     * 村庄状态
     */
    private Integer countryStatus;
}
