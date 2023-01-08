package com.country.countryside.role.bean;

import com.country.countryside.config.id.Id;
import com.country.countryside.config.id.UUIDGenerator;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Data
public class TbRole implements Serializable {

    /**
     * 主键
     */
    @Id(strategy = UUIDGenerator.class)
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 是否删除 0：否 1：是
     */
    private Integer isDelete;
}
