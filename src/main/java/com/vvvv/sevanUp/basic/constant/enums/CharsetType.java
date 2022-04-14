package com.vvvv.sevanUp.basic.constant.enums;

public enum CharsetType {
    UTF8("UTF-8"),
    GBK("gbk");
    private final String charsetType;
    CharsetType(String charsetType) {
        this.charsetType = charsetType;
    }
    public String getCharset() {
        return charsetType;
    }
}
