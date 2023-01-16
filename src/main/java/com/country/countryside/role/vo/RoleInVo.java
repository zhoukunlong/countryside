package com.country.countryside.role.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 角色入参实体
 * @author zhoukunlong
 * @date 2023/01/16
 * @since
 * @see
 */
@Data
public class RoleInVo {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
}
