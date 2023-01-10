package com.mt.serivce;


import com.mt.database.es.EsZcMachine;
import com.mt.database.es.EsZcMachineSave;
import io.searchbox.client.JestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
public class EsZcMachineServiceImpl{
    @Resource
    private JestClient jestClient;

    @Resource
    private ZcJestApiService zcJestApiService;


    public void saveBatch(List<EsZcMachineSave> list) {
        try {
            if (!CollectionUtils.isEmpty(list)) {
                for (EsZcMachineSave esZcMachine : list) {
                    esZcMachine.setId(esZcMachine.getMachineUuid());
                    esZcMachine.setUpdateTime(new Date().getTime());
                    zcJestApiService.upsertEsZcMachine(esZcMachine);
                }
            }
        } catch (Exception ex) {
            log.error("es保存异常{}", ex.toString());
        }
    }

}
