package com.wenba.arris.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @ Author     ：wenshuai.zhang
 * @ Date       ：Created in 2:19 PM 2018/10/31
 * @ Version    ：1.0
 * @ Modified By：
 * @ Description：Mac地址工具
 */
public final class MacUtil {
    /**
     * Media Access Control Address，媒体访问控制地址
     * 在网络中唯一标示一个网卡，一台电脑会有一或多个网卡，每个网卡有一个唯一的MAC地址
     * 此处可以为 mac地址/hostname/虚拟编号
     */
    private static byte[] mac = null;

    private MacUtil() {
    }

    /**
     * 获取mac地址
     *
     * @return 10进制字符串
     * @throws SocketException
     * @throws UnknownHostException
     */
    public static String getMac10() throws SocketException, UnknownHostException {
        byte[] mac = getMac();
        if (mac != null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                // 字节转换为整数
                int temp = mac[i] & 0xff;
                sb.append(String.valueOf(Math.abs(temp)));
            }
            // System.out.println("mac10 " + sb.toString());
            return sb.toString();
        }
        return null;
    }

    /**
     * 获取mac地址
     *
     * @return 16进制字符串
     * @throws SocketException
     * @throws UnknownHostException
     */
    public static String getMac16() throws SocketException, UnknownHostException {
        byte[] mac = getMac();
        if (mac != null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                // 字节转换为整数
                int temp = mac[i] & 0xff;
                // 整数转为16进制字符串
                String str = Integer.toHexString(temp);
                // 一个字节最多映射两个16进制位，若只有一位，则前面补0
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            // System.out.println("mac16 " + sb.toString());
            return sb.toString();
        }
        return null;
    }

    /**
     * 获取Mac地址
     *
     * @return 字符串
     * @throws UnknownHostException
     * @throws SocketException
     */
    public static String getMacStr() throws UnknownHostException, SocketException {
        return new String(getMac());
    }

    /**
     * 获取mac地址
     *
     * @return 字节数组
     */
    private static byte[] getMac() throws UnknownHostException, SocketException {
        // 双重校验锁
        if (mac == null) {
            synchronized (MacUtil.class) {
                if (mac == null) {
                    InetAddress ia = InetAddress.getLocalHost();
                    // 获取网卡
                    NetworkInterface ni = NetworkInterface.getByInetAddress(ia);
                    // 获取网卡地址，mac地址 48位，8 * 6 = 48 对应6个元素字节数组
                    mac = ni.getHardwareAddress();
                }
            }
        }
        return mac;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println(getMac10() + " - " + getMac16() + " - " + getMacStr());
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

}
