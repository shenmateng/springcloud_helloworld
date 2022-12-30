package com.mt.header;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.cnnotation.JowtoResponseBody;

import java.util.Objects;

/**
 * @author 段杨宇
 * @create 2019-01-21 17:04
 **/
public class CustomerJsonSerializer {

    private ObjectMapper mapper = new ObjectMapper();
    private JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    /**
     * @param clazz target type
     * @param include include fields
     * @param exclude filter fields
     */
    public void filter(Class<?> clazz, String[] include, String[] exclude) {
        if (Objects.equals(clazz, JowtoResponseBody.class)) {
            return;
        }
        if (include != null && include.length > 0) {
            jacksonFilter.include(clazz, include);
        }
        if (exclude != null && exclude.length > 0) {
            jacksonFilter.filter(clazz,exclude);
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        return mapper.writeValueAsString(object);
    }

    public void filter(JowtoResponseBody jowtoResponseBody) {
        this.filter(jowtoResponseBody.type(), jowtoResponseBody.include(), jowtoResponseBody.exclude());
    }
}
