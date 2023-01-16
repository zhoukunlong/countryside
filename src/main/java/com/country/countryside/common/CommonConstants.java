package com.country.countryside.common;

import java.text.SimpleDateFormat;

public class CommonConstants {

    /**
     * 时间格式化
     */
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

    /**
     * 默认用户密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 默认申请加入村庄内容
     */
    public static final String CONTENT = "申请加入";

    /**
     * 默认申请创建族谱内容
     */
    public static final String APPLY_PEDIGREE = "申请创建族谱";

    public interface Delete{
        /**
         * 已删除
         */
        Integer YES = 1;
        /**
         * 未删除
         */
        Integer NO = 0;
    }

    public interface Leaf{

        /**
         * 是
         */
        Integer YES = 1;

        /**
         * 否
         */
        Integer NO = 0;
    }

    public interface UserResource{

        /**
         * 注册
         */
        Integer REGIST = 0;

        /**
         * 他人添加
         */
        Integer ADD = 1;
    }

    public interface ProcessStatus{

        /**
         * 审核中
         */
        Integer PROCESSING = 0;

        /**
         * 审核通过
         */
        Integer PASS = 1;

        /**
         * 审核不通过
         */
        Integer NOTPASS = 2;
    }

    public interface ROLE{

        /**
         * 超级管理员
         */
        String administrator = "7a285172-5b39-4800-b7a7-5920cb3b7de7";

        /**
         * 族长
         */
        String patriarch = "233545bd-693a-47b3-abf7-a8b0a6404016";

        /**
         * 管理者
         */
        String manager = "57a5b24b-6442-4ab5-97eb-e42a2a28761f";
    }
}
