package com.wenba.arris.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String getStringUTF8(String string) throws UnsupportedEncodingException {
        return new String(string.getBytes("iso8859-1"), "utf-8");
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().equals("");
    }

    public static boolean AllHasValue(String... values) {
        boolean result = true;
        if (values != null && values.length > 0) {
            for (String value : values) {
                if (isNullOrEmpty(value)) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    public static String getString(Object value) {
        if (value == null)
            return "";
        else
            return value.toString();
    }

    public static String getEnumFormat(String value) {
        if (!isNullOrEmpty(value)) {
            if (value.startsWith(",") == false) {
                value = "," + value;
            }
            if (value.endsWith(",") == false) {
                value = value + ",";
            }
        }
        return value;
    }

    public static String[] trimStrings(String[] strings) {
        if (strings != null) {
            ArrayList<String> temp = new ArrayList<String>();
            for (String string : strings) {
                if (!isNullOrEmpty(string)) {
                    temp.add(string);
                }
            }
            String[] result = new String[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                result[i] = String.valueOf(temp.get(i));
            }
            return result;
        } else {
            return null;
        }
    }
}
