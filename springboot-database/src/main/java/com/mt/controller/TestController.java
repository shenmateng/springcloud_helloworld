package com.mt.controller;

import com.github.pagehelper.PageInfo;
import com.mt.ResponseResult.ResponseResult;
import com.mt.database.ShopifyBatchLogReqVO;
import com.mt.database.TemplateShopifyLogVO;
import com.mt.service.OnNewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
