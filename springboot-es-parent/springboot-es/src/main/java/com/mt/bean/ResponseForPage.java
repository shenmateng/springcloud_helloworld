package com.mt.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaolm
 * @data 2019/1/19
 */
public class ResponseForPage<T> implements Serializable {

    private List<T> list;

    private Long total = 0L;

    public List<T> getList() {
        return list == null ? new ArrayList<>(): list;
    }

    public ResponseForPage<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public Long getTotal() {
        return total == null ? 0 : total;
    }

    public ResponseForPage<T> setTotal(Long total) {
        this.total = total;
        return this;
    }
}
