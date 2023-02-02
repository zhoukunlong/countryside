package com.country.countryside.countryside.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 工单信息出参
 */
@Data
public class ProcessOutVo implements Serializable {

    private String id;

    /**
     * 申请标题
     */
    private String processTitle;

    /**
     * 申请内容
     */
    private String processContent;

    /**
     * 申请人
     */
    private String userId;

    /**
     * 申请人
     */
    private String approveRoleId;

    /**
     * 村庄id
     */
    private String countryId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;
}
