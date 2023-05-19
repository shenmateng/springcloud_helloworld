package com.mt.database;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer currentPage = 1;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer maxResults = 10;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
}
