package com.country.countryside.user.vo;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Data
public class UserInVo implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    @NotBlank(message = "名称不能为空")
    private String userName;

    /**
     * 登入账户
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private String birthTime;

    /**
     *  所属村庄id
     */
    private String countryId;

    /**
     * 性别 0：男  1：女
     */
    @NonNull
    private Integer userSex;

    /**
     * 用户状态 0:生  1：死
     */
    @NonNull
    private Integer userStatus;

    /**
     * 联系方式
     */
    private String userTel;

    /**
     * 身份证
     */
    private String userNo;

    /**
     * 用户描述
     */
    private String userDesc;

    /**
     * 用户头像
     */
    private String userImg;

    /**
     * 过期时间
     */
    private String expireTime;

    /**
     * 其他信息
     */
    private String other;
}
