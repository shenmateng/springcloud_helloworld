package com.mt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public static String getIpRange(String baseIp, int subnetMask) throws UnknownHostException {
        // 将IP地址转换为长整型
        long baseIpLong = ipToLong(baseIp);

        // 计算结束IP的长整型地址
        long endIpLong = baseIpLong | (0xFFFFFFFFL >> subnetMask);

        // 转换回IP地址
        String endIp = longToIp(endIpLong);

        return baseIp + "-" + endIp;
    }

    public static void main(String[] args) {

        String filePath = "/Users/mateng/Downloads/agg.txt";
        // 创建文件对象
        File file = new File(filePath);
        // 检查文件是否存在
        if (!file.exists()) {
            System.out.println("文件不存在！");

        }
        String json = "";
        // 使用 BufferedReader 来读取文件内容
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line;
//                System.out.println(line);  // 打印每一行
                // 使用 split 方法将 IP 地址按 "." 分割
                String[] ipParts = line.split("\\.");
                String ipPart = ipParts[2];
                int subnetMask = 24;
                if(ipPart.equals("0")){
                    subnetMask = 16; // /16掩码，表示10.118.0.0 到 10.118.255.255
                }
                String ipRange = getIpRange(line, subnetMask);
                System.out.println(ipRange);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            // 输入IP和子网掩码
//            String baseIp = "10.54.13.0";
//            int subnetMask = 16; // /16掩码，表示10.118.0.0 到 10.118.255.255
//
//            String ipRange = getIpRange(baseIp, subnetMask);
//            System.out.println(ipRange);
//
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }
}