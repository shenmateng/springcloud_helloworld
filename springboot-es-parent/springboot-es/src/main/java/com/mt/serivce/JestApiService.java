package com.mt.serivce;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.aliases.AddAliasMapping;
import io.searchbox.indices.aliases.ModifyAliases;
import io.searchbox.indices.settings.UpdateSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JestApiService {

    @Autowired
    private JestClient jestClient;


    public <T> T searchDocById(T t){
        T z = null;
        try {
            String index = (String) t.getClass().getMethod("index").invoke(t);
            String type = (String) t.getClass().getMethod("type").invoke(t);
            String id = (String) t.getClass().getMethod("id").invoke(t);
            JestResult jr = jestClient.execute(new Get.Builder(index,id).build());
            boolean isSucceeded = jr.isSucceeded();
            if(!isSucceeded){
                log.error(jr.getJsonString());
            }else{
                z = (T) jr.getSourceAsObject(t.getClass(),false);
                try {
                    t.getClass().getMethod("setId",String.class).invoke(z,id);
                } catch (Exception ex) {
                    log.error("",ex);
                }
            }
        } catch (Exception ex) {
            log.error("",ex);
        }
        return z;
    }


    public boolean createIndex(String index,String setting,String mapping){
        try {
            JestResult jr = jestClient.execute(new CreateIndex.Builder(index).settings(setting).mappings(mapping).build());
            boolean isSucceeded = jr.isSucceeded();
            if(!isSucceeded){
                log.error(jr.getJsonString());
            }
            return isSucceeded;
        } catch (Exception ex) {
            log.error("",ex);
        }
        return false;
    }

    public boolean indicesExists(String index){
        try {
            JestResult jr = jestClient.execute(new IndicesExists.Builder(index).build());
            return jr.isSucceeded();
        } catch (Exception ex) {
            log.error("",ex);
        }
        return false;
    }


    public boolean addAlias(String index, String alias) {
        try {
            AddAliasMapping build = new AddAliasMapping.Builder(index, alias).build();
            JestResult jr = jestClient.execute(new ModifyAliases.Builder(build).build());
            boolean isSucceeded = jr.isSucceeded();
            if(!isSucceeded){
                log.error(jr.getJsonString());
            }
            return isSucceeded;
        } catch (Exception ex) {
            log.error("",ex);
        }
        return false;
    }

    public boolean updateSettings(String index,String setting) {
        try {
            JestResult jr = jestClient.execute(new UpdateSettings.Builder(setting).build());
            boolean isSucceeded = jr.isSucceeded();
            if(!isSucceeded){
                log.error(jr.getJsonString());
            }
            return isSucceeded;
        } catch (Exception ex) {
            log.error("",ex);
        }
        return false;
    }

}
