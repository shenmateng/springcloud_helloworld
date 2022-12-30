package com.mt.database;

import javax.validation.constraints.Size;

/**
 * @author zhaolm
 * @data 2019/1/23
 */
public class PageQuery extends BaseQuery{
    @Size(max = 100)
    private String keyWord;

    private int currentPage = 1;
    private int maxResults = 10;

    private String selectMachineUuid;

    private String selectId;

    /**
     * 0-无，1-全选
     */
    private Integer selectFlag = 0;

    /**
     * 组织ID
     */
    private String orgUuid;

    public String getKeyWord() {
/*        if(StringUtils.isEmpty(keyWord)){
            return keyWord;
        }
        return keyWord.replaceAll("\\\\","\\\\\\\\");*/
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public String getSelectMachineUuid() {
        return selectMachineUuid;
    }

    public void setSelectMachineUuid(String selectMachineUuid) {
        this.selectMachineUuid = selectMachineUuid;
    }

    public String getSelectId() {
        return selectId;
    }

    public void setSelectId(String selectId) {
        this.selectId = selectId;
    }

    public Integer getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(Integer selectFlag) {
        this.selectFlag = selectFlag;
    }

    public String getOrgUuid() {
        return orgUuid;
    }

    public void setOrgUuid(String orgUuid) {
        this.orgUuid = orgUuid;
    }
}
