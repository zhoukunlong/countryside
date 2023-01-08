package com.country.countryside.role.bean;

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

    private String id;

    private String userId;

    private String roleId;

    private String relatName;

    private String createTime;

    private String updateTime;

    private Integer isDelete;
}
