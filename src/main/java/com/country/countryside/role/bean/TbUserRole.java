package com.country.countryside.role.bean;

import com.country.countryside.config.id.Id;
import com.country.countryside.config.id.UUIDGenerator;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Data
public class TbUserRole implements Serializable {

    /**
     * 关联id
     */
    @Id(strategy = UUIDGenerator.class)
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 村庄id
     */
    private String countryId;

    /**
     * 角色名称
     */
    private String relatName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 是否删除标识 0：否 1：是
     */
    private Integer isDelete;
}
