package com.country.countryside.role.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 角色关联入参实体
 * @author zhoukunlong
 * @date 2023/01/16
 * @since
 * @see
 */
@Data
public class UserRoleInVo {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    /**
     * 村庄id
     */
    @NotBlank(message = "村庄id不能为空")
    private String countryId;

    /**
     * 角色名称
     */
    private String relatName;
}
