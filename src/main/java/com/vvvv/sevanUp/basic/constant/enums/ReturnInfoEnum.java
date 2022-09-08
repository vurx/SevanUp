package com.vvvv.sevanUp.basic.constant.enums;

/**
 * 返回信息枚举
 */
public enum ReturnInfoEnum {
    SUCCESS(0, "success"),
    ERROR(-1, "error"),
    NET_ERROR(-9001, "network error."),
    URL_PARSE_ERROR(-9002, "url parse error."),
    TRANSFER_DATESTR_ERROR(-9003, "transfer dateStr error."),
    QY_WECHAT_MSGCRYPT_ERROR(-9004, "qy wechat msgcrypt error."),
    UNABLE_FIND_EXTENDERS_ERROR(-9005, "can't find the extender."),
    UNKNOWN_ERROR(-9999, "unknow error."),
    ;
    private Integer code;
    private String msg;

    ReturnInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
