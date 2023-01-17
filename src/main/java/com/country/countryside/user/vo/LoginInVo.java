package com.country.countryside.user.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登入入参
 * @author zhoukunlong
 * @date 2023/01/17
 * @since
 * @see
 */
@Data
public class LoginInVo implements Serializable {

    /**
     * 账号（账号，手机号）
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
