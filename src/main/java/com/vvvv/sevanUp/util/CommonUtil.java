package com.vvvv.sevanUp.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static String generateToken(String userCode) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String str = df.format(date);
        return MD5.getMd5(userCode + str);
    }

    public static String regex(String content, String pattern, int groupNum) {
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        if (m.find()) {
            return m.group(groupNum);
        }

        return null;
    }

    public static List<String> regexList(String content, String pattern, int groupNum) {
        List<String> list = new ArrayList<>();
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        while (m.find()) {
            list.add(m.group(groupNum));
        }

        return list;
    }

    public static String excuteJs(String js) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            Object eval = engine.eval(js);
            return eval.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
