package com.mt.init;

import com.mt.worker.WorkerCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInit implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private EsInit esInit;

    private static boolean IS_INIT = false;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if(IS_INIT){
           return;
        }else {
            IS_INIT = true;
        }
        log.info("DataInit Start......");
        WorkerCenter.singleton().init();
        esInit.init();
        log.info("DataInit End......");
    }
}
