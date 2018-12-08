package com.wenba.arris.common.utils;

/**
 * Created by imiiot on 2018/7/12.
 */

public final class UpperLowerUtil {
    private UpperLowerUtil() {
    }

    public static String firstCharacterLower(String str) {
        if(str != null && !"".equals(str)) {
            char c = str.charAt(0);
            return Character.toLowerCase(c) + str.substring(1);
        } else {
            return null;
        }
    }

    public static String firstCharacterUpper(String str) {
        if(str != null && !"".equals(str)) {
            char c = str.charAt(0);
            return Character.toUpperCase(c) + str.substring(1);
        } else {
            return null;
        }
    }
}