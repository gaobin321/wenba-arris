package com.wenba.arris.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * @author I_MI_IOT
 * @version 1.0
 * @description json工具
 * @date 2016年7月8日 上午11:27:57
 */
public final class UtilFastJson {

    /**
     * @description 私有化构造函数，防止在外部创建实例,保证单例
     */
    private UtilFastJson() {
    }

    /**
     * @param obj
     * @return String
     * @description 序列化对象转化为JSON串
     */
    public static String toJson(Object obj) {
        if (obj != null) {
            return JSON.toJSONString(obj);
        }
        return null;
    }

    /**
     * @param json
     * @param clazz
     * @return T
     * @description 反序列化JSON串为对象
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        if (json != null) {
            return JSON.parseObject(json, clazz);
        }
        return null;
    }

    /**
     * 反序列化为List<T>
     *
     * @param json
     * @param clazz List内元素类型
     * @return
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        if (json != null) {
            return JSON.parseArray(json, clazz);
        }
        return null;
    }

    /**
     * 反序列表为Map<K,V>
     *
     * @param json
     * @param kClazz map 键类型
     * @param vClazz map 值类型
     * @return
     */
    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClazz, Class<V> vClazz) {
        if (json != null) {
        }
        return null;
    }

	/*public static void main(String[] args) {
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("k1","v1");
		map.put("k2","v2");
		map.put("k3",3);
		System.out.println(UtilFastJson.toJson(map));
		String[] mess = new String[3];
		mess[0] = "o1";
		mess[1] = "o2";
		System.out.println(UtilFastJson.toJson(mess));
	}*/
}