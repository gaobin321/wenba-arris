package com.wenba.arris.common.utils;

import java.util.UUID;

/**
 * UUID 无序
 * Created by imiiot on 2016/11/6.
 */
public class UUIDUtil {
    private static final Integer MAC_ID = 1;

    public static long uuid() {
        return uuid(MAC_ID);
    }

    public static long uuid(int macId) {
        if (macId <= 0) {
            macId = MAC_ID;
        }

        int hashCodeV = UUID.randomUUID().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }

        // 0 代表前面补充0
        // 10 代表长度为10
        // d 代表参数为正数型
        return Long.valueOf(macId + String.format("%010d", hashCodeV));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(uuid());
        }
    }


}