package com.mt.ai;

import lombok.Data;

import java.util.List;


@Data
public class AIResultQuestionVO {

    /**
     * 题目
     */
    private String quContent;

    /**
     * 解答题答案
     */
    private String answer;

    /**
     * 解答题或者选择题的解析
     */
    private String analysis;

    /**
     * 选项列表
     */
    private List<AIOptionVO> allOption;




}
