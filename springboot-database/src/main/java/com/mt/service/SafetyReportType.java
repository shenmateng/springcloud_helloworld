package com.mt.service;

/**
 *  安全报告类型
 * @author mzj
 */
public enum SafetyReportType {

    /**
     *  日报
     */
    DAILY(0),
    /**
     *  月报
     */
    MONTHLY(1),

    /**
     *  周报
     */
    WEEKLY(2);

    private final int code;

    SafetyReportType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    // 数字转枚举方法
    public static SafetyReportType fromCode(int code) {
        for (SafetyReportType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("无效的主机类型编码: " + code);
    }

}
