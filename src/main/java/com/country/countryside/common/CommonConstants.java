package com.country.countryside.common;

import java.text.SimpleDateFormat;

public class CommonConstants {

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

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
}
