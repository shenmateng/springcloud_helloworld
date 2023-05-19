package com.mt.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mt.database.*;
import com.mt.mapper.onnew.BasicSkuMapper;
import com.mt.mapper.onnew.TemplateShopifyLogExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private BasicSkuMapper basicSkuMapper;

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
        System.out.println(basicSkuDO);
        basicSkus.forEach(o->{
            String sku = o.getSku();
            Integer id = o.getId();
            String dianya = sku.substring(sku.length() -2,sku.length());
            String wudianya = sku.substring(0,sku.length()-2);
            String ss = "update basic_sku set sku_without_voltage = '"+wudianya+"',voltage = '"+dianya+"' where sku = '"+sku+"' and is_delete = 0 and id = "+id+";";
            String sss = "delete from basic_sku where id = "+ id+ ";";
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


}
