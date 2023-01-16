package com.country.countryside.countryside.bean;

import com.country.countryside.config.id.Id;
import com.country.countryside.config.id.UUIDGenerator;
import lombok.Data;

import java.io.Serializable;

/**
 * 审核工单
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Data
public class TbProcess implements Serializable {

    /**
     * 主键
     */
    @Id(strategy = UUIDGenerator.class)
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
     * 申请状态 0：审批中 1：审批通过 2：审批不通过
     */
    private Integer status;

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

    /**
     * 是否删除标识 0：否  1：是
     */
    private Integer isDelete;
}
