package com.wenba.arris.common.utils;

import java.util.Collection;
import java.util.Map;


/**
 * @author I_MI_IOT
 * @version 1.0
 * @description 验证数据
 * @date 2016年3月7日 下午5:27:45
 * <p>
 * 注：
 * 判断一条数据是否满足要求，直接与要求对比即可。
 * 判断所有数据是否都满足要求，先假设所有数据都满足，只要找到一个不满足的即可。
 */
public final class UtilData {

    private UtilData() {
    }

    /**
     * @param value
     * @return boolean
     * @description 数据是否为null
     */
    public static boolean isNull(Object value) {
        return (value == null);
    }

    /**
     * @param value
     * @return boolean
     * @description 数据是否不为null
     */
    public static boolean isNotNull(Object value) {
        return !isNull(value);
    }

    /**
     * @param values
     * @return boolean
     * @description 所有数据是否都为null
     */
    public static boolean isAllNull(Object... values) {
        boolean result = true;
        // 数组为null，则所有数据都为null，满足要求

        // 数组不为null
        if (isNotNull(values)) {
            for (Object value : values) {
                // 存在一个数据不为null，不满足要求
                if (isNotNull(value)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * @param values
     * @return boolean
     * @description 所有数据是否都不为null
     */
    public static boolean isAllNotNull(Object... values) {
        boolean result = true;

        // 数组为null，则所有数据都为null，不满足要求
        if (isNull(values)) {
            result = false;

            // 数组不为null
        } else {
            for (Object value : values) {
                // 存在一个数据为null，不满足要求
                if (isNull(value)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * @param value
     * @return boolean
     * @description 数据是否为有效数据
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidData(Object value) {
        // 数据为null，无效数据，不满足要求

        //数组不为null
        if (isNotNull(value)) {
            if (value instanceof Number) {
                // Number	数值大于0
                Number number = (Number) value;
                return isValidNumber(number);
            } else if (value instanceof String) {
                // String	length>0时，数据有效
                String str = (String) value;
                if (str.length() > 0) {
                    return true;
                }
            } else if (value instanceof Collection) {
                // Collection	size()>0，数据有效
                Collection<Object> collection = (Collection<Object>) value;
                if (collection.size() > 0) {
                    return true;
                }
            } else if (value instanceof Map) {
                // Map	并未实现 Collection 接口
                // Map	size>0，数据有效
                Map<Object, Object> map = (Map<Object, Object>) value;
                if (map.size() > 0) {
                    return true;
                }
            } else if (value instanceof Object[]) {
                // 数组	length>0，数据有效
                Object[] array = (Object[]) value;
                if (array.length > 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * @param value
     * @return boolean
     * @description 数据是否为无效数据
     */
    public static boolean isInvalidData(Object value) {
        return !isValidData(value);
    }

    /**
     * @param values
     * @return boolean
     * @description 是否所有的数据都为有效数据
     */
    public static boolean isAllValidData(Object... values) {
        boolean result = true;

        // 数组为null，则所有数据都为无效数据，不满足要求
        if (isNull(values)) {
            result = false;

            // 数组不为null
        } else {
            for (Object value : values) {
                // 存在一个数据为无效数据，不满足要求
                if (isInvalidData(value)) {
                    result = false;
                    break;
                }
            }
        }

        return result;

    }

    /**
     * @param values
     * @return boolean
     * @description 是否所有的数据都为无效数据
     */
    public static boolean isAllInvalidData(Object... values) {
        boolean result = true;
        // 数组为null，所有数据都为无效数据，满足要求

        // 数组不为null
        if (isNotNull(values)) {
            for (Object value : values) {
                // 存在一个数据为有效数据，不满足要求
                if (isValidData(value)) {
                    result = false;
                    break;
                }
            }
        }

        return result;

    }

    /**
     * @param value
     * @return boolean
     * @description Number 是否大于 0
     * 精度 byte < short < int < long < float < double，且自动向上转型，所以直接转为double类型值，判断是否大于0即可
     */
    private static boolean isValidNumber(Number value) {
        return (value.doubleValue() > 0);
    }
}
