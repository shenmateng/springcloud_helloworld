package com.mt.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 主机防护开关枚举值
 * @author zhouchangsong
 */

@Getter
@AllArgsConstructor
public enum MachineSwitchEnum {

    G01_FHKG_1("G01_FHKG_1","系统防护开关"),
    G01_FHKG_2("G01_FHKG_2","应用网络访问过滤开关"),
    G01_FHKG_3("G01_FHKG_3","进程外连监控开关"),
    G01_FHKG_4("G01_FHKG_4","进程监听端口监控开关"),
    G01_FHKG_5("G01_FHKG_5","IP层流量过滤开关"),
    G01_FHKG_6("G01_FHKG_6","ASP模型流量过滤开关"),
    G01_FHKG_7("G01_FHKG_7","DNS解析监控开关"),
    G01_FHKG_8("G01_FHKG_8","文件防护开关"),
    G01_FHKG_9("G01_FHKG_9","文件读防护开关"),
    G01_FHKG_10("G01_FHKG_10","进程创建监控开关"),
    G01_FHKG_11("G01_FHKG_11","进程结束监控开关"),
    G01_FHKG_12("G01_FHKG_12","进程防护开关"),
    G01_FHKG_13("G01_FHKG_13","应用白名单控制开关"),
    G01_FHKG_14("G01_FHKG_14","动态链接库/驱动执行控制"),
    G01_FHKG_15("G01_FHKG_15","文件哈希黑名单"),
    G01_FHKG_16("G01_FHKG_16","操作系统加固"),
    G01_FHKG_17("G01_FHKG_17","Agent自身防护"),
    G01_FHKG_18("G01_FHKG_18","端口扫描防护"),
    G01_FHKG_19("G01_FHKG_19","应用漏洞防护"),
    G01_FHKG_20("G01_FHKG_20","威胁行为防护"),
    G01_FHKG_21("G01_FHKG_21","已知WebShell自动隔离"),
    G01_FHKG_22("G01_FHKG_22","未知WebShell实时防护"),
    G01_FHKG_23("G01_FHKG_23","未知SQL注入漏洞防护"),
    G01_FHKG_24("G01_FHKG_24","未知上传漏洞防护"),
    G01_FHKG_25("G01_FHKG_25","Struts2漏洞防护"),
    G01_FHKG_26("G01_FHKG_26","反序列化漏洞防护"),
    G01_FHKG_27("G01_FHKG_27","任意文件读取漏洞防护"),
    G01_FHKG_28("G01_FHKG_28","命令执行漏洞防护"),
    G01_FHKG_31("G01_FHKG_31","文件监控与防护"),
    G01_FHKG_32("G01_FHKG_32","堡垒锁"),
    G01_FHKG_33("G01_FHKG_33","进程外连控制开关"),
    G01_FHKG_34("G01_FHKG_34","RDP暴力破解监控开关"),
    G01_FHKG_35("G01_FHKG_35","注册表防护开关"),
    G01_FHKG_36("G01_FHKG_36","磁盘保护开关"),
    G01_FHKG_37("G01_FHKG_37","文件（创建、写）关闭日志开关"),
    G01_FHKG_38("G01_FHKG_38","二进制文件监控"),
    G01_FHKG_39("G01_FHKG_39","脚本文件监控"),
    G01_FHKG_40("G01_FHKG_40","防护开关-21"),
    G01_FHKG_41("G01_FHKG_41","进程外连日志"),
    G01_FHKG_42("G01_FHKG_42","进程创建日志"),
    G01_FHKG_43("G01_FHKG_43","进程连接内网IP日志"),
    G01_FHKG_44("G01_FHKG_44","缓冲区溢出防护"),
    G01_FHKG_45("G01_FHKG_45","一键隔离"),
    G01_FHKG_46("G01_FHKG_46","应用堡垒锁"),
    G01_FHKG_47("G01_FHKG_47","系统堡垒锁"),
    G01_FHKG_48("G01_FHKG_48","微隔离防火墙"),
    G01_FHKG_49("G01_FHKG_49","web服务器溢出攻击防护"),
    G01_FHKG_50("G01_FHKG_50","web服务器文件名解析漏洞防护"),
    G01_FHKG_51("G01_FHKG_51","禁止浏览畸形文件"),
    G01_FHKG_52("G01_FHKG_52","仅允许下列请求类型"),
    G01_FHKG_53("G01_FHKG_53","禁止下载特定类型文件"),
    G01_FHKG_54("G01_FHKG_54","网页浏览实时防护"),
    G01_FHKG_55("G01_FHKG_55","http请求头防护"),
    G01_FHKG_56("G01_FHKG_56","自动屏蔽扫描器"),
    G01_FHKG_57("G01_FHKG_57","x-forwarded-for防护"),
    G01_FHKG_58("G01_FHKG_58","系统卷影保护"),
    G01_FHKG_59("G01_FHKG_59","禁止对外服务进程执行危险命令"),
    G01_FHKG_60("G01_FHKG_60","禁止对外服务进程创建危险扩展名文件"),
    G01_FHKG_61("G01_FHKG_61","禁止对外服务进程执行非正常扩展名可执行文件"),
    G01_FHKG_62("G01_FHKG_62","禁止对外服务进程创建可执行文件"),
    G01_FHKG_63("G01_FHKG_63","禁止窃取系统内存密码"),
    G01_FHKG_64("G01_FHKG_64","禁止添加用户到管理员组"),
    G01_FHKG_65("G01_FHKG_65","禁止非系统进程创建autorun.inf文件"),
    G01_FHKG_66("G01_FHKG_66","禁止非系统进程创建Usp10.dll文件"),
    G01_FHKG_67("G01_FHKG_67","禁止非系统进程创建lpk.dll文件"),
    G01_FHKG_68("G01_FHKG_68","禁止非系统进程尝试劫持系统引导过程"),
    G01_FHKG_69("G01_FHKG_69","禁止非系统进程尝试劫持系统登录过程"),
    G01_FHKG_70("G01_FHKG_70","禁止非系统进程尝试劫持系统启动过程"),
    G01_FHKG_71("G01_FHKG_71","禁止非系统进程尝试劫持EXE执行过程"),
    G01_FHKG_72("G01_FHKG_72","禁止非系统进程尝试劫持自启动过程"),
    G01_FHKG_73("G01_FHKG_73","禁止非系统进程通过组策略劫持开机过程"),
    G01_FHKG_74("G01_FHKG_74","禁止非系统进程通过组策略劫持关机过程"),
    G01_FHKG_75("G01_FHKG_75","禁止非系统进程修改系统服务"),
    G01_FHKG_76("G01_FHKG_76","禁止非系统进程修改MSI提权"),
    G01_FHKG_77("G01_FHKG_77","禁止非系统进程通过修改屏保程序劫持启动过程"),
    G01_FHKG_78("G01_FHKG_78","禁止更改系统可执行文件(windows系统进程例外)"),
    G01_FHKG_79("G01_FHKG_79","禁止更改系统引导文件(windows系统进程例外)"),
    G01_FHKG_80("G01_FHKG_80","禁止粘连键命令被篡改"),
    G01_FHKG_81("G01_FHKG_81","禁止非系统进程在系统目录下创建可执行文件"),
    G01_FHKG_82("G01_FHKG_82","禁止非系统进程修改LSP"),
    G01_FHKG_83("G01_FHKG_83","禁止加载无数字签名驱动"),
    G01_FHKG_84("G01_FHKG_84","基于行为的缓冲区溢出防护"),
    G01_FHKG_85("G01_FHKG_85","禁止对外服务进程修改SSH认证文件"),
    G01_FHKG_86("G01_FHKG_86","禁止对外服务进程更改Linux系统配置文件"),
    G01_FHKG_87("G01_FHKG_87","禁止对外服务进程添加定时任务"),
    G01_FHKG_88("G01_FHKG_88","禁止对外服务进程修改账户信息"),
    G01_FHKG_89("G01_FHKG_89","禁止对外服务进程修改系统日志"),
    G01_FHKG_90("G01_FHKG_90","禁止对外服务进程运行隐藏的进程"),
    G01_FHKG_91("G01_FHKG_91","禁止对外服务进程创建危险的定时任务脚本"),
    G01_FHKG_92("G01_FHKG_92","禁止对外服务进程编译文件"),
    G01_FHKG_93("G01_FHKG_93","禁止更改系统登录认证相关文件"),
    G01_FHKG_94("G01_FHKG_94","禁止反连shell"),
    G01_FHKG_95("G01_FHKG_95","禁止nc进程创建shell"),
    G01_FHKG_96("G01_FHKG_96","禁止创建密码文件的符号链接"),
    G01_FHKG_97("G01_FHKG_97","禁止添加系统启动项"),
    G01_FHKG_98("G01_FHKG_98","禁止在系统二进制目录下面创建隐藏文件夹"),
    G01_FHKG_99("G01_FHKG_99","禁止对外服务进程监听原始套接字"),
    G01_FHKG_100("G01_FHKG_100","禁止在/dev/shm/下创建可执行文件"),
    G01_FHKG_101("G01_FHKG_101","禁止进程自我复制"),

    ;


    /**
     * 请求参数key
     */
    private final String code;
    /**
     * 开关名字
     */
    private final String remark;

    /**
     * 根据code 获取remark
     * @param code
     * @return
     */
    public static String codeToRemark(Object code) {
        return Arrays.stream(values())
                .filter(p -> String.valueOf(p.code).equals(String.valueOf(code)))
                .findFirst()
                .map(MachineSwitchEnum::getRemark)
                .orElse("");
    }

    /**
     * 根据code 获取remark
     * @return
     */
    public static Map<String,Integer> allCode() {
        Map<String,Integer> mapss = new LinkedHashMap<>();
        for (MachineSwitchEnum value : values()) {
            String code1 = value.getCode();
            mapss.put(code1,3);
        }

        return mapss;

    }


}
