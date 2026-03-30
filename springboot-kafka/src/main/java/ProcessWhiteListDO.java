


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ProcessWhiteListDO implements Serializable {
    private static final long serialVersionUID = -24558476486941710L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 加白类型： 0：全部类型 1：端口扫描  2：暴力破解  3：恶意文件  4：勒索病毒防护  5：恶意进程执行  6：高危系统操作  7：关键文件变更  8：Web后门
     */
    private Integer whitenType;
    /**
     * 执行用户
     */
    private Integer executeUser;
    /**
     * 进程名
     */
    private String processName;
    /**
     * 进程路径
     */
    private String processPath;
    /**
     * 进程命令行
     */
    private String processLine;
    /**
     * 源ip
     */
    private String sourceIp;
    /**
     * 本地用户
     */
    private String localUser;
    /**
     * 客体类型 0：文件  1：进程   2：驱动
     */
    private Integer objectType;
    /**
     * 客体内容
     */
    private String objectContent;
    /**
     * 加白原因
     */
    private String remark;
    /**
     * 配置人员
     */
    private String configUser;
    /**
     * 配置时间
     */
    private Date configTime;
    /**
     * 是否修改过（0 否  1是）
     */
    private Integer updateStatus;

}
