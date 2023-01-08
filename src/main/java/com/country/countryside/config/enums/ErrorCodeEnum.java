package com.country.countryside.config.enums;

import org.springframework.util.StringUtils;

public enum ErrorCodeEnum {

    ERROR_0xbdc10001("0xbdc10001", "参数错误","Parameter error");

    private String code;
    //	记录到日志的信息
    private String logMsg;
    //	返回给前端的信息
    private String tips;



    ErrorCodeEnum(String code, String tips, String logMsg) {
        this.code = code;
        this.logMsg = logMsg;
        this.tips = tips;
    }

    public String getCode() {
        return this.code;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public String getTips() {
        return tips;
    }

    /**
     * 根据错误码获取返回给前端的提示信息
     * @param code
     * @return
     */
    public static String getTips(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for(ErrorCodeEnum errorCodeEnum:ErrorCodeEnum.values()){
            if (code.equals(errorCodeEnum.getCode())) {
                return errorCodeEnum.getTips();
            }
        }
        return null;
    }
    /**
     * 根据错误码，获取日志的错误信息
     * @param code
     * @return
     */
    public static String getLogMsg(String code){
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for(ErrorCodeEnum errorCodeEnum:ErrorCodeEnum.values()){
            if (code.equals(errorCodeEnum.getCode())) {
                return errorCodeEnum.getLogMsg();
            }
        }
        return null;
    }
}
