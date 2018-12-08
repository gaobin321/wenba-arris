package com.wenba.arris.common.utils.reflect;


import com.wenba.arris.common.utils.UpperLowerUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by imiiot on 2018/5/9.
 */
public final class ReflectUtil {
    private ReflectUtil() {
    }

    public static List<String> getFieldListByGetter(Class clazz) {
        List<String> fields = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();

            // getter
            String get = methodName.substring(0, 3);
            String field = null;

            // is
            String is = methodName.substring(0, 2);
            if ("get".equals(get)) {
                field = UpperLowerUtil.firstCharacterLower(methodName.substring(3));
            } else if ("is".equals(is)) {
                field = UpperLowerUtil.firstCharacterLower(methodName.substring(2));
            }

            // Object 基类，存在 getter 方法 getClass，所以需要排除 class
            if (field != null && isExclude(field, "class")) {
                fields.add(field);
            }
        }
        return fields;
    }

    public static Map<String, Object> getFieldValueMapByGetter(Object obj) {
        Map<String, Object> fieldValueMap = new HashMap<>();

        Class clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();

            // getter
            String get = methodName.substring(0, 3);
            String field = null;

            // is
            String is = methodName.substring(0, 2);
            if ("get".equals(get)) {
                field = UpperLowerUtil.firstCharacterLower(methodName.substring(3));
            } else if ("is".equals(is)) {
                field = UpperLowerUtil.firstCharacterLower(methodName.substring(2));
            }

            // Object 基类，存在 getter 方法 getClass，所以需要排除 class
            if (field != null && isExclude(field, "class")) {
                try {
                    Object value = method.invoke(obj);
                    if (value != null) {
                        fieldValueMap.put(field, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        return fieldValueMap;
    }

    /**
     * 排除 特定field对象
     *
     * @param field
     * @return
     */
    private static boolean isExclude(String field, String... excludes) {
        if (field != null && !"".equals(field)) {
            List<String> excludeList = new ArrayList<>();

            if (excludes != null && excludes.length > 0) {
                for (String exclude : excludes) {
                    excludeList.add(exclude);
                }
            }

            return !excludeList.contains(field);
        }
        return false;
    }

}
