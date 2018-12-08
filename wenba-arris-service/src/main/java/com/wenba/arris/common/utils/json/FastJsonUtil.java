package com.wenba.arris.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @author I_MI_IOT
 * @version 1.0
 * @description json工具
 * @date 2016年7月8日 上午11:27:57
 */
public final class FastJsonUtil {

    /**
     * @description 私有化构造函数，防止在外部创建实例,保证单例
     */
    private FastJsonUtil() {
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
     * 反序列化多级泛型
     * new TypeReference<A<B<C>>>(){}
     * 通过 JSON.parseObject(json, new TypeReference<A<B<C>>>() {}); 解析为多级泛型对象
     *
     * @param json
     * @param typeReference 如 new TypeReference<Map<String,String>>(){}
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        if (json != null) {
            return JSON.parseObject(json, typeReference);
        }
        return null;
    }


    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("k1", "v1");
//        map.put("k2", "v2");
//        map.put("k3", -3);
//        String jsonMap = FastJsonUtil.toJson(map);
//        System.out.println(jsonMap);
//        Map<String, Object> parseMap = FastJsonUtil.parseObject(jsonMap,new TypeReference<Map<String, Object>>(){});
//        System.out.println(parseMap);
//        String[] arr = new String[2];
//        arr[0] = "o1";
//        arr[1] = "o2";
//        String jsonArr = FastJsonUtil.toJson(arr);
//        System.out.println(jsonArr);
//        List<String> parstList = FastJsonUtil.parseList(jsonArr, String.class);
//        System.out.println(parstList);
//        String[] arr1 = new String[parstList.size()];
//        parstList.toArray(arr1);
//        System.out.println(arr1);
//
//        String objValue = "{\"k1\":\"v1\",\"k2\":\"v2\",\"k3\":3}";
//        System.out.print(objValue);
//
//        String[] arr = ["a","b"];
////        String name = FastJsonUtil.parseObject("武警",String.class);
////        System.out.print(name);
    }
}