package com.vvvv.sevanUp.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class XssUtil {

    private static Map<String, String> xssMap = new LinkedHashMap<>();

    static {
        xssMap.put("[s|S][c|C][r|R][i|I][p|P][t|T]", "*");
    }

    public static String cleanXSS(String value) {
        if (StringUtil.isNotEmpty(value)) {
            for (Map.Entry<String, String> t : xssMap.entrySet()) {
                value = value.replace(t.getKey(), t.getValue());
            }
        }
        return value;
    }
}
