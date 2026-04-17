package com.fri.domain.scanevent;

import lombok.Data;

import java.util.Objects;

/**
 * @author ：mateng
 * @version ：2.1.5.2
 * @program ：event
 * @date ：Created in 2023/10/21 10:04
 * @description ：
 */

@Data
public class ScanIpVO {

    /**
     * ips
     */
    private String scanIp;

    /**
     * 扫描时间
     */
    private Long scanTime;

    /**
     * ip+端口
     */
    private String ip;


    public ScanIpVO() {
    }

    public ScanIpVO(String scanIp, Long scanTime, String ip) {
        this.scanIp = scanIp;
        this.scanTime = scanTime;
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScanIpVO scanIpVO = (ScanIpVO) o;
        return Objects.equals(scanIp, scanIpVO.scanIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scanIp);
    }
}
