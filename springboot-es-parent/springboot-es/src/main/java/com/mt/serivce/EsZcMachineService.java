package com.mt.serivce;

import com.mt.bean.ResponseForPage;
import com.mt.config.AssetConfig;
import com.mt.database.AssetQueryForEs;
import com.mt.database.GetMachinesByUserUuidP;
import com.mt.database.es.EsZcMachine;
import com.mt.mapper.es.BasicSkuMapper;
import com.mt.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Service
public class EsZcMachineService {


    @Resource
    private EsZcQueryBaseService esZcQueryBaseService;

    @Resource
    private BasicSkuMapper basicSkuMapper;

    public List<EsZcMachine> getMachinesByUserUuid(GetMachinesByUserUuidP getMachinesByUserUuidP) throws Exception {

        AssetQueryForEs assetQueryForEs = new AssetQueryForEs();
        assetQueryForEs.setCurrentPage(1);
        assetQueryForEs.setMaxResults(AssetConfig.INDEX_MAX_RESULT_WINDOW);
        assetQueryForEs.setUserUuid(getMachinesByUserUuidP.getUserUuid());
        assetQueryForEs.setOrgQueryFlag(0);
        if(StringUtils.isNotBlank(getMachinesByUserUuidP.getTagUuid())) {
            List<String> tagNames = basicSkuMapper.selectTagNamesByTagUuid(getMachinesByUserUuidP.getTagUuid());
            if(!CollectionUtils.isEmpty(tagNames)) {
                assetQueryForEs.setMachineTags(DataUtils.joinByComma(tagNames));
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        assetQueryForEs.setFilters(map);
        ResponseForPage<EsZcMachine> rfp = esZcQueryBaseService.listAssets(assetQueryForEs, EsZcMachine.class, new EsZcMachine().index(), 1);
        log.info("获取机器id集合---{}", rfp.getList());
        return rfp.getList();
    }

}
