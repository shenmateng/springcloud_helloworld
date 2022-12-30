package com.mt.bean;

import java.io.Serializable;


public class Sort implements Serializable {

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    private String field;

    private String order;

    public Sort() {

    }

    public Sort(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
