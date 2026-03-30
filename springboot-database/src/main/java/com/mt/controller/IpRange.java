package com.mt.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpRange {
    
    // 将IP地址字符串转换为整型
    private static long ipToLong(String ipAddress) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] ipBytes = inetAddress.getAddress();
        long ipLong = 0;
        for (int i = 0; i < ipBytes.length; i++) {
            ipLong |= (ipBytes[i] & 0xFFL) << (24 - (i * 8));
        }
        return ipLong;
    }

    // 将整型转换为IP地址字符串
    private static String longToIp(long ipLong) {
        return String.format("%d.%d.%d.%d",
                (ipLong >> 24) & 0xFF,
                (ipLong >> 16) & 0xFF,
                (ipLong >> 8) & 0xFF,
                ipLong & 0xFF);
    }

    // 计算IP范围
    public static String getIpRange(String baseIp) throws UnknownHostException {
        // 将IP地址转换为长整型
        long baseIpLong = ipToLong(baseIp);
        
        // 计算结束IP的长整型地址（假设这是一个/24子网，意味着255.255.255.0）
        long endIpLong = baseIpLong | 0xFFL;  // 末尾变为255
        
        // 转换回IP地址
        String endIp = longToIp(endIpLong);
        
        return baseIp + "-" + endIp;
    }

    public static void main(String[] args) {
        try {
            // 输入IP地址
            String baseIp1 = "10.118.0.0";
            String baseIp2 = "10.188.345.0";
            
            // 获取并输出IP段
            System.out.println("IP Range 1: " + getIpRange(baseIp1)); // 10.118.0.0-10.118.255.255
            System.out.println("IP Range 2: " + getIpRange(baseIp2)); // 10.188.345.0-10.188.345.255
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}