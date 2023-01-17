package com.country.countryside.config.enums;

import org.springframework.util.StringUtils;

public enum ErrorCodeEnum {

    //公共部分
    ERROR_0xbdc10001("0xbdc10001", "参数错误","Parameter error"),

    //村庄
    ERROR_0xbdc20001("0xbdc20001", "申请加入的村庄不存在","countryside is not exist"),
    ERROR_0xbdc20002("0xbdc20002", "申请工单不存在或已被撤回","the order is not exist"),

    //用户
    ERROR_0xbdc30001("0xbdc30001", "用户已在其他村庄，需要先退出再申请","Parameter error"),
    ERROR_0xbdc30002("0xbdc30002", "用户未登入，请先登入","please login in"),
    ERROR_0xbdc30003("0xbdc30003", "用户已在其他村庄，不能创建村庄","Parameter error"),
    ERROR_0xbdc30004("0xbdc30004", "用户名或者密码错误","Parameter error"),

    //族谱
    ERROR_0xbdc40001("0xbdc10001", "参数错误","Parameter error"),

    //工单
    ERROR_0xbdc50001("0xbdc50001", "存在在途工单，等待审批完成再操作","Parameter error")
    ;

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
