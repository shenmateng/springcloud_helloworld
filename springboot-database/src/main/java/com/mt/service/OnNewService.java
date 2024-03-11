package com.mt.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.monitorjbl.xlsx.StreamingReader;
import com.mt.database.*;
import com.mt.mapper.onnew.BasicSkuMapper;
import com.mt.mapper.onnew.IpSeetMapper;
import com.mt.mapper.onnew.SystemWhiteListGroupMachineMapper;
import com.mt.mapper.onnew.TemplateShopifyLogExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/1/6 19:18
 * @since ：
 */

@Service
@Slf4j
public class OnNewService {

    @Value("${spring.inspection.pmpath}")
    private String url;
    @Value("${spring.inspection.suffix}")
    private String url1;

    @Resource
    private TemplateShopifyLogExtMapper templateShopifyLogExtMapper;

    @Resource
    private SystemWhiteListGroupMachineMapper systemWhiteListGroupMachineMapper;

    @Resource
    private BasicSkuMapper basicSkuMapper;

    @Resource
    private IpSeetMapper ipSeetMapper;

    private static List<IpSeet> allip = new ArrayList<>();

    /**
     * 日志分页展示
     *
     * @param vevorBatchLogReqVO 查询条件 站点和平台
     * @return 分页结果集
     */
    public PageInfo<TemplateShopifyLogVO> queryAll(ShopifyBatchLogReqVO vevorBatchLogReqVO) {
        PageHelper.startPage(vevorBatchLogReqVO.getCurrentPage(), vevorBatchLogReqVO.getPageSize());
        TemplateShopifyLogDO templateShopifyLogDO = new TemplateShopifyLogDO();
        templateShopifyLogDO.setSite(vevorBatchLogReqVO.getSiteCode());
        templateShopifyLogDO.setPlatfrom(vevorBatchLogReqVO.getPlatform());
        Date startTime = vevorBatchLogReqVO.getStartTime();
        String startTimes = DateUtil.format(startTime, "yyyy-MM-dd");
        templateShopifyLogDO.setStartTimes(startTimes);
        List<TemplateShopifyLogDO> templateShopifyLogs = templateShopifyLogExtMapper.queryAlls(templateShopifyLogDO);
        PageInfo<TemplateShopifyLogDO> pageInfoDo = new PageInfo<>(templateShopifyLogs);
        List<TemplateShopifyLogVO> templateShopifyLogVos = new ArrayList<>(16);
        for (TemplateShopifyLogDO templateShopifyLog : templateShopifyLogs) {
            Date startTime1 = templateShopifyLog.getStartTime();
            System.out.println(startTime1);
            TemplateShopifyLogVO templateShopifyLogVO = tempDoToVo(templateShopifyLog);
            templateShopifyLogVos.add(templateShopifyLogVO);
        }
        PageInfo<TemplateShopifyLogVO> pageInfoVo = new PageInfo<>();
        pageInfoVo.setList(templateShopifyLogVos);
        pageInfoVo.setTotal(pageInfoDo.getTotal());
        pageInfoVo.setPageNum(pageInfoDo.getPageNum());
        pageInfoVo.setPageSize(pageInfoDo.getPageSize());
        return pageInfoVo;
    }

    public TemplateShopifyLogVO tempDoToVo(TemplateShopifyLogDO templateShopifyLogDO) {
        TemplateShopifyLogVO templateShopifyLogVO = new TemplateShopifyLogVO();
        templateShopifyLogVO.setId(templateShopifyLogDO.getId());
        templateShopifyLogVO.setTaskId(templateShopifyLogDO.getTaskId());
        templateShopifyLogVO.setPlatfrom(templateShopifyLogDO.getPlatfrom());
        templateShopifyLogVO.setSite(templateShopifyLogDO.getSite());
        templateShopifyLogVO.setStartTime(templateShopifyLogDO.getStartTime());
        templateShopifyLogVO.setEndTime(templateShopifyLogDO.getEndTime());
        templateShopifyLogVO.setStatus(templateShopifyLogDO.getStatus());
        templateShopifyLogVO.setSuccessData(templateShopifyLogDO.getSuccessData());
        templateShopifyLogVO.setFailData(templateShopifyLogDO.getFailData());
        templateShopifyLogVO.setCreateBy(templateShopifyLogDO.getCreateBy());
        templateShopifyLogVO.setUpdateBy(templateShopifyLogDO.getUpdateBy());
        templateShopifyLogVO.setDelete(templateShopifyLogDO.getDelete());
        return templateShopifyLogVO;
    }


    public void resku(){
        List<BasicSkuDO> basicSkus = basicSkuMapper.querySku();
        BasicSkuDO basicSkuDO = basicSkuMapper.BasicSkuMapper();
        List<String> machineUuid = new ArrayList<>();
        machineUuid.add("235d59763454ae901badfdaea87afd06");
        String id = "045b2851ec914b62896ac6faaa0cc798";
        List<ProtectConfBackupsMachine> protectConfBackupsMachines = basicSkuMapper.queryByProIdAndMachineUuid(id, machineUuid);
        System.out.println(basicSkuDO);
        basicSkus.forEach(o->{
            String sku = o.getSku();
            Integer id1 = o.getId();
            String dianya = sku.substring(sku.length() -2,sku.length());
            String wudianya = sku.substring(0,sku.length()-2);
            String ss = "update basic_sku set sku_without_voltage = '"+wudianya+"',voltage = '"+dianya+"' where sku = '"+sku+"' and is_delete = 0 and id = "+id+";";
            String sss = "delete from basic_sku where id = "+ id1+ ";";
            System.out.println(ss);
        });
        System.out.println(url);
        System.out.println(url1);
        System.out.println(url + url1);


        List<ZcMachine> offMachineByAll = basicSkuMapper.findOffMachineByAll();
        String value = "30";
        long pushTime = Long.parseLong(value);
        Date date = new Date();
        List<ZcMachine> pushOffMachine = new ArrayList<>();
        offMachineByAll.forEach(o -> {
            if (ObjectUtils.isNotEmpty(o.getOnlineTime())) {
                Date onlineTime = o.getOnlineTime();
                String datePoor = getDatePoor(onlineTime, date);
                System.out.println(datePoor);
                long dateTimePoor = getDateTimePoor(onlineTime, date);
                if (dateTimePoor > pushTime) {
                    pushOffMachine.add(o);
                }
            }
        });
    }


    public static long getDateTimePoor( Date startDate ,Date endDate ) {
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少分钟
        return diff / nm;  //520分钟
    }

    public static String getDatePoor( Date startDate ,Date endDate ) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000 ;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        //long day = diff / nd;
        // 计算差多少小时
        long hour = diff  / nh;
        // 计算差多少分钟
        long min = diff  % nh / nm;

        // 计算差多少秒//输出结果
        long sec = diff  % nh % nm / ns;
        return   hour + "时" + min + "分"+ sec + "秒";  //17时20分32秒
    }


    public List<TemplateShopifyLogVO> queryStudent(){
        List<TemplateShopifyLogVO> templateShopifyLogVOS = new ArrayList<>();

        for(int i = 0; i<3;i++){
            TemplateShopifyLogVO templateShopifyLogVO = new TemplateShopifyLogVO();

            templateShopifyLogVO.setId(i);
            templateShopifyLogVO.setCreateBy("mateng"+ i);
            templateShopifyLogVO.setStatus(i+10);
            templateShopifyLogVOS.add(templateShopifyLogVO);
        }

        return null;
    }



    public Map<String,List<String>> selectMachineUuidsByTagName(SelectMachineUuidsByTagUuidTagP selectMachineUuidsByTagUuidTagP){
        List<ZcMachineTagName> machineUuids = new ArrayList<>();
        String userUuid = selectMachineUuidsByTagUuidTagP.getUserUuid();
        String tagUuid = selectMachineUuidsByTagUuidTagP.getTagUuid();
        Integer onlineStatus = selectMachineUuidsByTagUuidTagP.getOnlineStatus();

        //查询这个用户管理了哪些机器(用户创建了这些分组，分组选了这些机器；并且还要是这个用户管理了这些机器)
        machineUuids = templateShopifyLogExtMapper.selectMachineUuidsByTagName(userUuid, tagUuid, onlineStatus);
        List<String> whiteListId = new ArrayList<>();
        whiteListId.add("D93FA07017044C46873D23E07797AAB0");
        List<SystemWhiteListAndMachine> systemWhiteListAndMachines = templateShopifyLogExtMapper.listByAllIds(whiteListId);
        Map<String,List<String>> tagUuidsMap = new HashMap<>();
        machineUuids.forEach(o->{
            String tagName = o.getTagName();
            List<ZcMachineTagName1> macUuids = o.getMacUuids();
            List<String> uuids = new ArrayList<>();
            macUuids.forEach(mac-> uuids.add(mac.getUuid()));
            tagUuidsMap.put(tagName,uuids);
        });
        JSON o = (JSON)JSON.toJSON(tagUuidsMap);
        String s = o.toJSONString();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, List<String>>>() {}.getType();
        Map<String, List<String>> map = gson.fromJson(s, type);
        List<SystemWhiteListGroupMachineDO> systemWhiteListGroupMachineDOS = new ArrayList<>();
        map.forEach((k,v)-> v.forEach(sss->{
            SystemWhiteListGroupMachineDO systemWhiteListGroupMachineDO = new SystemWhiteListGroupMachineDO();
            systemWhiteListGroupMachineDO.setGroupName(k);
            systemWhiteListGroupMachineDO.setMachineUuid(sss);
            systemWhiteListGroupMachineDO.setWhiteListId("21312321");
            systemWhiteListGroupMachineDOS.add(systemWhiteListGroupMachineDO);
        }));
       systemWhiteListGroupMachineMapper.insertBatch(systemWhiteListGroupMachineDOS);
       List<ZcTagUserIp> zcTagFixUserIps = new ArrayList<>();
        ZcTagUserIp zcTagUserIps = new ZcTagUserIp();
        zcTagUserIps.setUuid("dsfsdf");
        zcTagUserIps.setTagName("sdfsdf");
        zcTagUserIps.setTagUuid(tagUuid);
        zcTagUserIps.setUserUuid(userUuid);
        zcTagUserIps.setIpRange("sdfsdf");
        zcTagUserIps.setIpStartIndex(322332L);
        zcTagUserIps.setIpEndIndex(3422L);
        zcTagUserIps.setCreateTime(new Date());
        zcTagFixUserIps.add(zcTagUserIps);
       systemWhiteListGroupMachineMapper.insertBatch2(zcTagFixUserIps);
       systemWhiteListGroupMachineMapper.insertBatch2(zcTagFixUserIps);
        List<DeleteZcMachineBasicVO> deleteZcMachineBasicVOS = new ArrayList<>();
        DeleteZcMachineBasicVO deleteZcMachineBasicVO = new DeleteZcMachineBasicVO();
        DeleteZcMachineBasicVO deleteZcMachineBasicVO1 = new DeleteZcMachineBasicVO();
        deleteZcMachineBasicVO.setMachineUuid("da192308940c5ecf979986048e89eb05");
        deleteZcMachineBasicVO1.setMachineUuid("1cf1f413b35b6dd2ef3cda14494f61ff");
        deleteZcMachineBasicVO.setUserUuid("bdb6297e902147a9a013aee5a63432dd");
        deleteZcMachineBasicVO1.setUserUuid("97764f83161f4bfc9b6e0bf3b5198542");
        deleteZcMachineBasicVOS.add(deleteZcMachineBasicVO);
        deleteZcMachineBasicVOS.add(deleteZcMachineBasicVO1);
        List<String> userUuid11 = new ArrayList<>();
        String ss = "ca7545f5b40746fb95633b57ef6a01e8,bdb6297e902147a9a013aee5a63432dd";
        String ss1 = "95e1d6c74e2b4fa9b2dbc65cc4d9de66,b05423314c5343bc92723e338c07df1b";

        userUuid11.add(ss);
        userUuid11.add(ss1);
        List<UserInfo> usersByUserUuidLists = systemWhiteListGroupMachineMapper.getUsersByUserUuidLists(userUuid11);
        List<MachineAndUserInfoQuery> userInfos = new ArrayList<>();
        MachineAndUserInfoQuery userInfo = new MachineAndUserInfoQuery();
        userInfo.setUuid("993100f6c9db4eb7b37379f20dcf5b79");
        userInfo.setMachineUuid("3dcbb36c439d980d15a088d2847095cd");
        MachineAndUserInfoQuery userInfo1 = new MachineAndUserInfoQuery();
        userInfo1.setUuid("993100f6c9db4eb7b37379f20dcf5b79");
        userInfo1.setMachineUuid("a23b3c09567062b388da3cd175a9b4fa");
        userInfos.add(userInfo);
        userInfos.add(userInfo1);
        String ssw = "[\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"55788bb0bc822a9c9db981348a4db759\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"55788bb0bc822a9c9db981348a4db759\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"bda1ab966e6a5b3ac2379c20f2b04b3f\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"bda1ab966e6a5b3ac2379c20f2b04b3f\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"a23b3c09567062b388da3cd175a9b4fa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"a23b3c09567062b388da3cd175a9b4fa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"3dcbb36c439d980d15a088d2847095cd\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"3dcbb36c439d980d15a088d2847095cd\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"9e6324a91ca94601004ef19fef64144f\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"9e6324a91ca94601004ef19fef64144f\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"6562d6e54deb3fdd0387025bbaaed003\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"6562d6e54deb3fdd0387025bbaaed003\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"85ca3424a96e20a403e11a0752918646\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"85ca3424a96e20a403e11a0752918646\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"21c7d6d9e1502af945fef874df1b8c7f\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"21c7d6d9e1502af945fef874df1b8c7f\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"21c7d6d9e1502af945fef874df1b8c5d\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"21c7d6d9e1502af945fef874df1b8c5d\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"73499386cf4d5efc47fd833327923aa5\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"73499386cf4d5efc47fd833327923aa5\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"d933345de25b7f72d669068f08982126\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"d933345de25b7f72d669068f08982126\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"842b97a87f790155a328a059908e83a0\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"842b97a87f790155a328a059908e83a0\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"91c16a831a8f57c7a1d12192100f7033\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"91c16a831a8f57c7a1d12192100f7033\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"1c2fe9c58ce3dc269f475601b7b44d98\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"1c2fe9c58ce3dc269f475601b7b44d98\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"993100f6c9db4eb7b37379f20dcf5b79\",\n" +
                "    \"machineUuid\": \"847ea2c752dea32ba937f52f1a225d1e\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"uuid\": \"ba62febb436f44df82d49db5df8b9669\",\n" +
                "    \"machineUuid\": \"847ea2c752dea32ba937f52f1a225d1e\"\n" +
                "  }\n" +
                "]";

        List<MachineAndUserInfoQuery> mapList  = JSON.parseArray(ssw, MachineAndUserInfoQuery.class);
        List<ZcMachineTagDO> zcMachineTagDOS = systemWhiteListGroupMachineMapper.selectMachineTagsList(mapList);

        List<String> whiteListIdss = new ArrayList<>();
        whiteListIdss.add("21312321");
        List<SystemWhiteListGroupMachineDO> systemWhiteListGroupMachineDOS1 = systemWhiteListGroupMachineMapper.listByAllIds(whiteListIdss);
        String ssss = "1ec1821b74234167bc28c34199ef6c65";
        EventWhitelistAutoAdd eventWhitelistAutoAdd = systemWhiteListGroupMachineMapper.queryByEventWhiteId(ssss);
        System.out.println(systemWhiteListGroupMachineDOS1);

        return null;
    }


    public List<TemplateShopifyLogVO> selectIp(){
        List<IpSeet> ipSeets = ipSeetMapper.queryip(16777473L);
        return new ArrayList<>();

    }







    /**
     * 匹配ip方法
     */
    public void toIpData() throws Exception {
        String source = "D:\\data\\"+ "yun15" ;
        File sourceDir = new File(source);
        File[] files = sourceDir.listFiles();

        for(File excel:files){
            // 结果集汇总
            try {
                System.out.println(excel.getName() + " 文件开始处理！！！");
                if(excel.getName().endsWith(".xlsx")){
                    List<IpSeet> ips = toIpData(excel);
                    allip.addAll(ips);
                }
            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println(excel.getName() + " 文件处理异常！！！");
            }
        }

        toMatchWeakpassword("D:\\data\\电子系统\\第六批\\测试2.xlsx",allip);
        System.out.println(allip.size());
    }


    public static List<IpSeet> toIpData(File file) throws Exception {
        List<IpSeet> ipSeets = new ArrayList<>();
        // 解析excel文件
        org.apache.poi.ss.usermodel.Workbook wb = StreamingReader.builder().
                rowCacheSize(1_0000)
                .bufferSize(10240)
                .open(new FileInputStream(file));
        // 获取第一个工作表中上报地信息
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        for(org.apache.poi.ss.usermodel.Row row:sheet){
            try {
                // 当前行的索引号
                int rowNum = row.getRowNum();
                // 弱口令，高危漏洞，高危端口等sheet跳过第一行，从第二行开始解析，
                if (Objects.equals(0, rowNum)) { continue; }
                String minip = Optional.ofNullable(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()).orElse("");
                String maxip = Optional.ofNullable(row.getCell(1,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()).orElse("");
                String ipAddress = Optional.ofNullable(row.getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()).orElse("");

                IpSeet ipSeet = new IpSeet();
                ipSeet.setMinip(minip);
                ipSeet.setMaxip(maxip);
                ipSeet.setPosition(ipAddress);
                ipSeets.add(ipSeet);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return  ipSeets;
    }


    /**
     * 匹配弱口令数据是否是大屏资产
     * @param filePath 文件路径
     * @throws Exception 异常抛出
     */
    private static void toMatchWeakpassword(String filePath,List<IpSeet> allip) throws Exception {
        Map<String, JSONObject> ruleMap = new HashMap<>();

        //String dataFile = "D:\\data\\电子系统\\【截至20231109】需要确认是否为电子屏-一所源数据 - 副本 - 副本.xlsx";
        XSSFWorkbook dataWb = new XSSFWorkbook(new FileInputStream(filePath));
        XSSFSheet sheet1 = dataWb.getSheetAt(0);
        for(Row row:sheet1){
            if(row.getRowNum()<1) { continue; }
            String uuid = row.getCell(0).getStringCellValue();
            String ip = "";
            if(row.getCell(1)!=null) {
                ip = row.getCell(1).getStringCellValue();
            }

            boolean flag = false;
            String p = "";
            String c = "";
            String d = "";
            for(IpSeet url:allip){
                if(StringUtils.isNotBlank(ip) ){
                    String ipnumber = "";
                    try {
                        ipnumber = ipToString(ip);
                    }catch (Exception e){
                        System.out.println("转换ip出错");
                         continue;
                    }
                    String minip = url.getMinip();
                    String maxip = url.getMaxip();
                    int i = ipnumber.compareTo(minip);
                    int i1 = ipnumber.compareTo(maxip);
                    if(i>0 && i1<0){
                        try {
                            String position = url.getPosition();
                            SsqVO result = JSON.parseObject(position, SsqVO.class);
                             p = result.getP();
                             c = result.getC();
                             d = result.getD();
                            System.out.println("线索ID："+uuid+" 省："+p + " 市："+c +" 区："+d);
                            flag = true;
                            break;
                        }catch (Exception e){
                            System.out.println("转换json报错");
                        }
                    }
                }
            }
            if(flag){
                Cell cell = row.createCell(2);
                cell.setCellValue("是");
                Cell cell11 = row.createCell(3);
                cell11.setCellValue(p);
                Cell cell12 = row.createCell(4);
                cell12.setCellValue(c);
                Cell cell13 = row.createCell(5);
                cell13.setCellValue(d);
            }else {
                Cell cell = row.createCell(2);
                cell.setCellValue("否");
            }
        }
        // 写入到文件
        try (FileOutputStream outFile = new FileOutputStream(new File(filePath))) {
            dataWb.write(outFile);
        }
        System.out.println("ip匹配完成");
    }


    //IP转换成数字
    public static String ipToString(String ipAddress) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] bytes = inetAddress.getAddress();
        long result = 0;
        for (byte b : bytes) {
            result = (result << 8) | (b & 0xFF);
        }
        return result+"";
    }



    public static SsqVO fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, SsqVO.class);
    }

}
