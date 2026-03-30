package com.mt.ai;

import lombok.Data;


@Data
public class AIOptionVO {

    /**
     * 选择题答案
     */
    private Integer isTrue;

    /**
     * 选择题内容
     */
    private String option;

    /**
     * 正确答案解析
     */
    private String optionAnalysis;



}
