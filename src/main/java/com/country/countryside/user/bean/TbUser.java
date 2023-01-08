package com.country.countryside.user.bean;

import com.country.countryside.config.id.Id;
import com.country.countryside.config.id.UUIDGenerator;
import lombok.Data;
import java.io.Serializable;

/**
 * 用户信息
 * @author zhoukunlong
 * @date 2023/01/08
 */
@Data
public class TbUser implements Serializable {

    /**
     * 主键
     */
    @Id(strategy = UUIDGenerator.class)
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登入账户
     */
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
     * 是否删除标识 0：否  1：是
     */
    private Integer isDelete;

    /**
     *  所属村庄id
     */
    private String countryId;

    /**
     * 性别 0：男  1：女
     */
    private Integer userSex;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 用户状态 0:生  1：死
     */
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
     * 用户来源 0：用户注册  1：他人增加'
     */
    private Integer userResource;

    /**
     * 过期时间
     */
    private String expireTime;

    /**
     * 其他信息
     */
    private String other;
}
