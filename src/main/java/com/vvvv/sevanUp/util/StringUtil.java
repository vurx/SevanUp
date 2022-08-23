package com.vvvv.sevanUp.util;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String join(String... args) {
        return join(args, ":");
    }

    public static String join(Object[] array, String separator) {
        return join(array, separator, 0, array.length);
    }

    /**
     * 将数组中的元素以某个字符拼接
     * @param array ["aa","bb","cc"]
     * @param separator "|"
     * @param startIndex 0
     * @param endIndex 2
     * @return "aa|bb|cc"
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        } else {
            if (separator == null) {
                separator = "";
            }
        }

        int bufSize = endIndex - startIndex;
        if (bufSize <= 0) {
            return "";
        } else {
            bufSize *= (array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length();
            StringBuilder buf = new StringBuilder(bufSize);
            for (int i = startIndex; i < endIndex; i++) {
                if (i > startIndex)
                    buf.append(separator);
                if (array[i] != null)
                    buf.append(array[i]);
            }
            return buf.toString();
        }
    }
}
