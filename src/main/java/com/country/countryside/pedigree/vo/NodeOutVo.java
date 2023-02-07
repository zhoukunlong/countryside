package com.country.countryside.pedigree.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 节点返回信息
 * @author zhoukunlong
 * @date 2023/02/7
 * @since
 * @see
 */
@Data
public class NodeOutVo implements Serializable {

    /**
     * 树节点id
     */
    private String pedigreeId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 村庄id
     */
    private String countryId;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 用户状态：0:生  1：死
     */
    private Integer userStatus;

    /**
     * 用户头像
     */
    private String userImg;
}
