package com.vvvv.sevanUp.basic.constant.enums;

public enum StringEnum {
    WECHAT_PARK_TITLE("您已停车%s分钟%s秒！"),
    WECHAT_PARK_TITLE_HAND(WECHAT_PARK_TITLE.str + "(手动触发)"),
    WECHAT_PARK_TITLE_CHARGE(WECHAT_PARK_TITLE.str + "即将收费！"),
    WECHAT_PARK_DESCRIPTION("入场时间：%s \n通知时间：%s"),
    WECHAT_PARK_PIC("https://s3.bmp.ovh/imgs/2022/07/07/65cca3da6ab77929.jpg");
    private final String str;
    StringEnum(String str) {
        this.str = str;
    }
    public String getStr() {
        return str;
    }
}
