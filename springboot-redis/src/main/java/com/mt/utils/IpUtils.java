package com.mt.utils;



import java.math.BigInteger;
import java.net.InetAddress;

/**
 * @author: 段杨宇
 * @date: 17:44 2019/3/16
 */
public class IpUtils {

    /**
     * 是否为ipv4
     *
     * @param ip
     * @return
     */
    public static boolean isIpv4(String ip) {
        return null != ip && ip.split("\\.").length == 4 && IPAddressUtil.isIPv4LiteralAddress(ip);
    }


    /**
     * 是否为ipv6
     *
     * @param ip
     * @return
     */
    public static boolean isIpv6(String ip) {
        return null != ip && IPAddressUtil.isIPv6LiteralAddress(ip);
    }


    /**
     * 是否为内网ip
     *
     * @param ip
     * @return
     */
    public static boolean isIntranetIp(String ip) {
        try {
            if (isIpv4(ip) || isIpv6(ip)) {
                return InetAddress.getByName(ip).isSiteLocalAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 是否为公网ip
     *
     * @param ip
     * @return isSiteLocalAddress 站点本地地址(内网ip)
     * isMulticastAddress 组播地址
     * isAnyLocalAddress 通配符地址(0.0.0.0)
     * isLinkLocalAddress 链接本地地址
     * isLoopbackAddress 环回地址
     */
    public static boolean isInternetIp(String ip) {
        try {
            if (isIpv4(ip) || isIpv6(ip)) {
                InetAddress inetAddress = InetAddress.getByName(ip);
                return !inetAddress.isSiteLocalAddress() &&
                        !inetAddress.isMulticastAddress() &&
                        !inetAddress.isAnyLocalAddress() &&
                        !inetAddress.isLinkLocalAddress() &&
                        !inetAddress.isLoopbackAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * 判断一个IP是不是在一个网段下的
     * @param ipCidr 192.168.0.1/24, 192.168.0.25
     * @param ip
     * @return
     */
    public static boolean inRange(String ipCidr,String ip) {
        if(!isIpCidr(ipCidr) || !isIpv4(ip)){
            return false;
        }

        String[] ips = ip.split("\\.");
        int ipAddr =  (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8)
                | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(ipCidr.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = ipCidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr =  (Integer.parseInt(cidrIps[0]) << 24)
                | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8)
                | Integer.parseInt(cidrIps[3]);
        return (ipAddr & mask) == (cidrIpAddr & mask);
    }


    private static BigInteger ipBytes2BigInteger(byte[] input) {
        byte[] bytes =  new byte[1 + input.length];
        bytes[0] = 0;
        System.arraycopy(input, 0, bytes, 1, input.length);
        return new BigInteger(bytes);
    }

    /**
     * ip转十进制
     * @param ip
     * @return
     */
    public static BigInteger ip2BigInteger(String ip){
        boolean ipv4 = isIpv4(ip);
        boolean ipv6 = isIpv6(ip);

        if(!ipv4 && !ipv6){
            return null;
        }

        if(ipv4){
            byte[] bytes = IPAddressUtil.textToNumericFormatV4(ip);
            return bytes == null ? null : ipBytes2BigInteger(bytes);
        }else {
            byte[] bytes = IPAddressUtil.textToNumericFormatV6(ip);
            return bytes == null ? null : ipBytes2BigInteger(bytes);
        }
    }

    /**
     * 是否为合规的ip网段
     * @param ipCidr 192.168.0.1/24
     * @return
     */
    public static boolean isIpCidr(String ipCidr){
        if(ipCidr == null || "".equals(ipCidr)){
            return false;
        }

        String[] split = ipCidr.split("/");

        if(split.length != 2){
            return false;
        }

        String ip = split[0];
        int cidr = 0;

        try {
            cidr = Integer.parseInt(split[1]);
        }catch (Exception ignored){}

        if(cidr < 1 || cidr > 32){
            return false;
        }
        return isIpv4(ip);
    }

    /**
     * 是否为合规的ip范围
     * @param ip 192.168.0.1-192.168.0.255
     * @return
     */
    public static boolean isIpRange(String ip) {
        if (ip == null || "".equals(ip)) {
            return false;
        }

        String[] split = ip.split("-");
        if (split.length != 2) {
            return false;
        }
        String ipLeft = split[0];
        String ipRight = split[1];
        BigInteger left = ip2BigInteger(ipLeft);
        BigInteger right = ip2BigInteger(ipRight);
        if (left == null || right == null) {
            return false;
        }

        if (right.compareTo(left) < 0) {
            return false;
        }

        return IpUtils.isIpv4(ipLeft) && IpUtils.isIpv4(ipRight);
    }



}
