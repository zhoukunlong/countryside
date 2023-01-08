package com.country.countryside.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResult implements Serializable {

    private String code;

    private String msg;

    private Object data;

    public BaseResult(String code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回
     * @param data
     * @return
     */
    public static BaseResult success(Object data){
        return new BaseResult("0","success",data);
    }

    /**
     * 默认失败返回
     * @param data
     * @return
     */
    public static BaseResult fail(Object data){
        return new BaseResult("-1","error", data);
    }

    /**
     * 指定错误码返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static BaseResult fail(String code, String msg, Object data){
        return new BaseResult(code,msg, data);
    }
}
