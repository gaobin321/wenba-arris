package com.wenba.arris.common.utils;

/**
 * @author I_MI_IOT
 * @version 1.0
 * @description 字符串 驼峰标识 和 下划线 相互 转换
 * @date 2016年3月22日 下午2:02:48
 */
public final class UtilCamelUnderline {
    // 定义下划线字符，字符定义使用单引号，字符串定义使用双引号
    private final static char UNDERLINE = '_';

    private UtilCamelUnderline() {
    }

    /**
     * @param camelStr
     * @return String
     * @description 驼峰标识 转换为 下划线标识
     */
    public static String camelToUnderline(String camelStr) {
        if (UtilData.isValidData(camelStr)) {
            int len = camelStr.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char c = camelStr.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE);
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return camelStr;
        }
    }

    /**
     * @param underlineStr
     * @return String
     * @description 下划线标识 转换为 驼峰标识
     */
    public static String underlineToCamel(String underlineStr) {
        if (UtilData.isValidData(underlineStr)) {
            int len = underlineStr.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char c = underlineStr.charAt(i);
                if (UNDERLINE == c) {
                    if (++i < len) {
                        sb.append(Character.toUpperCase(underlineStr.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return underlineStr;
        }
    }

    /**
     * @param str
     * @return String
     * @description 首字母大写
     */
    public static String firstNameUpper(String str) {
        if (UtilData.isValidData(str)) {
            char c = str.charAt(0);
            if (Character.isUpperCase(c)) {
                return str;
            } else {
                StringBuilder temp = new StringBuilder(str);
                temp.deleteCharAt(0);
                return Character.toUpperCase(c) + temp.toString();
            }
        } else {
            return str;
        }
    }

    /**
     * @param str
     * @return String
     * @description 首字母小写
     */
    public static String firstNameLower(String str) {
        if (UtilData.isValidData(str)) {
            char c = str.charAt(0);
            if (Character.isLowerCase(c)) {
                return str;
            } else {
                StringBuilder temp = new StringBuilder(str);
                temp.deleteCharAt(0);
                return Character.toLowerCase(c) + temp.toString();
            }
        } else {
            return str;
        }
    }
}
