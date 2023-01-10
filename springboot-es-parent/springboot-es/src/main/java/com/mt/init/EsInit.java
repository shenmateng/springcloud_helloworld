package com.mt.init;


import com.mt.config.AssetConfig;
import com.mt.database.es.EsZcBase;
import com.mt.database.es.EsZcMachineSave;
import com.mt.serivce.JestApiService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EsInit {

    @Autowired
    private JestApiService jestApiService;

    public void init(){

        /*        jestApiService.deleteIndex("zc_*");*/
        log.info("EsInit Start......");
        //服务器资产
        try {
            EsZcBase z = new EsZcMachineSave();
            initIndex(z.index(),z.type(),z.alias(),z.mapping());
        } catch (Exception e) {
            log.error("",e);
        }

    }

    private void initIndex(String index,String type,String alias,String mapping){
        try {
            if(!jestApiService.indicesExists(index)){
                Settings settings = Settings.builder()
                        .put("index.number_of_shards", AssetConfig.INDEX_NUMBER_OF_SHARDS)
                        .put("index.number_of_replicas", AssetConfig.INDEX_NUMBER_OF_REPLICAS)
                        .put("index.max_result_window", AssetConfig.INDEX_MAX_RESULT_WINDOW)
                        .put("index.refresh_interval", AssetConfig.INDEX_REFRESH_INTERVAL)
                        .put("index.translog.durability", AssetConfig.INDEX_TRANSLOG_DURABILITY)
                        .put("index.translog.sync_interval", AssetConfig.INDEX_TRANSLOG_SYNC_INTERVAL)
                        .build();

                jestApiService.createIndex(index,settings.toString(),mapping);
                jestApiService.addAlias(index,alias);
            }else{
                Settings settings = Settings.builder()
                        .put("index.max_result_window", AssetConfig.INDEX_MAX_RESULT_WINDOW)
                        .put("index.refresh_interval", AssetConfig.INDEX_REFRESH_INTERVAL)
                        .put("index.translog.durability", AssetConfig.INDEX_TRANSLOG_DURABILITY)
                        .put("index.translog.sync_interval", AssetConfig.INDEX_TRANSLOG_SYNC_INTERVAL)
                        .build();

                jestApiService.updateSettings(index,settings.toString());
                jestApiService.addAlias(index,alias);
            }
        } catch (Exception e) {
            log.error("",e);
        }
    }
}
