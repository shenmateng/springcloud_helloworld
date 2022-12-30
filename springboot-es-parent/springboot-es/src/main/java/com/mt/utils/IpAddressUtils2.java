package com.mt.utils;


import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import inet.ipaddr.ipv4.IPv4Address;
import inet.ipaddr.ipv6.IPv6Address;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IpAddressUtils2 {

    public static boolean isIpv4(String ip){
        return ip != null && ip.split("\\.").length == 4 && new IPAddressString(ip).isIPv4();
    }

    public static boolean isIpv6(String ip){
        return ip != null && new IPAddressString(ip).isIPv6();
    }

    public static boolean isIp(String ip){
        return isIpv4(ip) || isIpv6(ip);
    }

    /**
     * 192.168.1.1-192.168.1.20
     * 判断是否是IP段
     */
    public static boolean isIpSegment(String ip){
        if(StringUtils.isBlank(ip))
            return false;
        String[] split = ip.split("-");
        if(split.length == 2){
            IPAddressString ips1 = new IPAddressString(split[0]);
            IPAddressString ips2 = new IPAddressString(split[1]);
            if(ips1.isIPv4() && ips2.isIPv4()){
                long v1 = ips1.getAddress().getValue().longValue();
                long v2 = ips2.getAddress().getValue().longValue();
                return v1 < v2;
            }else if(ips1.isIPv6() && ips2.isIPv6()){
                long v1 = ips1.getAddress().getValue().longValue();
                long v2 = ips2.getAddress().getValue().longValue();
                return v1 < v2;
            }
        }
        return false;
    }

    /**
     * 192.168.1.1-192.168.1.20,192.168.1.2-192.168.2.20
     * 判断是否是IP段
     */
    public static boolean isIpSegments(String ips){
        if(StringUtils.isBlank(ips)){
            return false;
        }
        String [] ipArray = ips.split(",");
        if (ipArray.length == 0) {
            return false;
        }
        for(String ip : ipArray){
            String[] split = ip.split("-");
            if(split.length != 2){
                return false;
            }
        }
        for(String ip : ipArray){
            String[] split = ip.split("-");
            IPAddressString ips1 = new IPAddressString(split[0]);
            IPAddressString ips2 = new IPAddressString(split[1]);
            if(ips1.isIPv4() && ips2.isIPv4()){
                long v1 = ips1.getAddress().getValue().longValue();
                long v2 = ips2.getAddress().getValue().longValue();
                if (!(v1 < v2)) {
                    return false;
                }
            }else if(ips1.isIPv6() && ips2.isIPv6()){
                long v1 = ips1.getAddress().getValue().longValue();
                long v2 = ips2.getAddress().getValue().longValue();
                if (!(v1 < v2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 192.168.10.0/24
     * 2001:db8:abcd:0012::/64
     */
    public static boolean isIpCIDR(String ip){
        if(StringUtils.isBlank(ip))
            return false;
        IPAddressString ips = new IPAddressString(ip);
        return ips.isIPAddress() && ips.isPrefixed();
    }

    /**
     * 192.168.10.0/24,192.168.10.0/24
     * 2001:db8:abcd:0012::/64
     */
    public static boolean isIpCIDRs(String ipArray){
        if(StringUtils.isBlank(ipArray)){
            return false;
        }
        String [] ipArrays = ipArray.split(",");
        for(String ip : ipArrays){
            IPAddressString ips = new IPAddressString(ip);
            if (!(ips.isIPAddress() && ips.isPrefixed())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取ip段范围
     * @param ip    ip段  或 CIDR
     * @return
     */
    public static Long[] getRange(String ip){
        if(isIpSegment(ip)){
            String[] split = ip.split("-");
            IPAddressString ips1 = new IPAddressString(split[0]);
            IPAddressString ips2 = new IPAddressString(split[1]);
            Long[] arr = new Long[2];
            arr[0] = ips1.getAddress().getValue().longValue();
            arr[1]  = ips2.getAddress().getValue().longValue();
            return arr;
        }else if(isIpCIDR(ip)){
            IPAddressString ips = new IPAddressString(ip);
            Long[] arr = new Long[2];
            IPAddress prefixBlock = ips.getAddress().toPrefixBlock();
            arr[0] = prefixBlock.getLower().getValue().longValue();
            arr[1] = prefixBlock.getUpper().getValue().longValue();
            return arr;
        }else if(isIp(ip)){
            if("0.0.0.0".equals(ip)){
                return null;
            }
            IPAddressString ips1 = new IPAddressString(ip);
            IPAddressString ips2 = new IPAddressString(ip);
            Long[] arr = new Long[2];
            arr[0] = ips1.getAddress().getValue().longValue();
            arr[1]  = ips2.getAddress().getValue().longValue();
            return arr;
        }
        return null;
    }

    /**
     * 获取ip段范围
     * @param ips    ip段  或 CIDR    多个段之间英文逗号隔开
     * @return
     */
    public static List<Long[]> getRanges(String ips){
        if(isIpSegments(ips)){
            List<Long[]> ipList = new ArrayList<>();
            String [] ipArray = ips.split(",");
            for(String ip : ipArray){
                String[] split = ip.split("-");
                IPAddressString ips1 = new IPAddressString(split[0]);
                IPAddressString ips2 = new IPAddressString(split[1]);
                Long[] arr = new Long[2];
                arr[0] = ips1.getAddress().getValue().longValue();
                arr[1]  = ips2.getAddress().getValue().longValue();
                ipList.add(arr);
            }
            return ipList;
        }else if(isIpCIDRs(ips)){
            List<Long[]> ipList = new ArrayList<>();
            String [] ipArray = ips.split(",");
            for(String ip : ipArray){
                IPAddressString ipString = new IPAddressString(ip);
                Long[] arr = new Long[2];
                IPAddress prefixBlock = ipString.getAddress().toPrefixBlock();
                arr[0] = prefixBlock.getLower().getValue().longValue();
                arr[1] = prefixBlock.getUpper().getValue().longValue();
                ipList.add(arr);
            }
            return ipList;
        }else if(isIp(ips)){
            List<Long[]> ipList = new ArrayList<>();
            if("0.0.0.0".equals(ips)){
                return null;
            }
            IPAddressString ips1 = new IPAddressString(ips);
            IPAddressString ips2 = new IPAddressString(ips);
            Long[] arr = new Long[2];
            arr[0] = ips1.getAddress().getValue().longValue();
            arr[1]  = ips2.getAddress().getValue().longValue();
            ipList.add(arr);
            return ipList;
        }
        return new ArrayList<>();
    }

    /**
     * 判断ip是否在某一个网段
     * @param cidr  192.168.1.64/26
     * @param ip    192.168.1.127
     * @return
     */
    public static boolean isInCirdRange(String cidr, String ip) {
        if (!cidr.contains("/")) {
            return false;
        }
        String[] ips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = cidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
                | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8)
                | Integer.parseInt(cidrIps[3]);

        return (ipAddr & mask) == (cidrIpAddr & mask);
    }

    /**
     * 判断ip是否在ip段 或 范围内
     * @param withinOrCidr  192.168.1.1-192.168.1.20,192.168.2.1-192.168.2.200 或 192.168.1.64/26,192.168.3.64/27
     * @param ipv4          192.168.1.1
     * @return
     */
    public static Boolean isWithinRangeOrCidrRange(String withinOrCidr,String ipv4){
        List<Long[]> ipList = getRanges(withinOrCidr);
        if (ipList.isEmpty()) {
            return false;
        }
        Boolean isIpv4 = isIpv4(ipv4);
        if (isIpv4) {
            for (Long[] ipv4s : ipList) {
                Long ipv4Long = ip2Long(ipv4);
                Long beginIpv4 = ipv4s[0];
                Long endIpv4 = ipv4s[1];
                if (ipv4Long.longValue() >= beginIpv4.longValue() && ipv4Long.longValue() <= endIpv4.longValue()) {
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    public static Long ip2Long(String ip){
        IPAddressString ips = new IPAddressString(ip);
        if(ips.isIPAddress()){
            return ips.getAddress().getValue().longValue();
        }
        return 0L;
    }

    public static String long2Ipv4(Long v){
        try {
            IPAddress ipAddress = new IPv4Address(v.intValue());
            return ipAddress.toNormalizedString();
        }catch (Exception ex){
        }
        return "";
    }

    /**
     * 值相同转出不同？
     * @param v
     * @return
     */
    public static String long2Ipv6(Long v){
        try {
            IPAddress ipAddress = new IPv6Address(new BigInteger(v.toString()));
            return ipAddress.toNormalizedString();
        }catch (Exception ex){
        }
        return "";
    }


    public static BigInteger ip2BigInteger(String ip){
        IPAddressString ips = new IPAddressString(ip);
        if(ips.isIPAddress()){
            return ips.getAddress().getValue();
        }
        return BigInteger.valueOf(0L);
    }

    }

