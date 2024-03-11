package com.mt.controller;

import com.github.pagehelper.PageInfo;
import com.mt.ResponseResult.ResponseResult;
import com.mt.database.*;
import com.mt.service.OnNewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/1/6 19:13
 * @since ：
 */
@Slf4j
@RestController
@RequestMapping("/controller-SyncMallOnNewController")
public class TestController {


    @Resource
    private OnNewService onNewService;

    /**
     * 日志分页展示
     * @param shopifyBatchLogReqVO 查询条件 站点和平台
     * @return 分页结果集
     */
    @PostMapping(value = "/queryAll")
    public ResponseResult<List<TemplateShopifyLogVO>> queryAll(@Valid @RequestBody ShopifyBatchLogReqVO shopifyBatchLogReqVO) {
        PageInfo<TemplateShopifyLogVO> pageInfo = onNewService.queryAll(shopifyBatchLogReqVO);
        return ResponseResult.successPage(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }



    @PostMapping(value = "/querySku")
    public void querySku(){
        onNewService.resku();
    }



    @GetMapping(value = "/queryStudent")
    public List<TemplateShopifyLogVO> queryStudent(){
        return onNewService.queryStudent();
    }



    @RequestMapping("/selectMachineUuidsByTagName")
    public void selectMachineUuidsByTagName(@RequestBody @Valid SelectMachineUuidsByTagUuidTagP selectMachineUuidsByTagUuidTagP) {
        onNewService.selectMachineUuidsByTagName(selectMachineUuidsByTagUuidTagP);

    }


    @PostMapping(value = "/selectIp")
    public List<TemplateShopifyLogVO> selectIp(){
        return onNewService.selectIp();
    }


    /**
     * 匹配ip库方法
     */
    @RequestMapping("/toIpData")
    @ResponseBody
    public String toIpData() throws Exception {
        onNewService.toIpData();
        return "成功";
    }
}
