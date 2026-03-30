package com.mt.ai;

import lombok.Data;


@Data
public class AutoQuestionVO {

    private String question;
    private String number;
    private String type;


    private Integer questionType;


    private Integer gradeLevel;


    /**
     * ???
     */
    private Integer subjectId;


    /**
     * 题目分值
     */
    private String score;

    /**
     * 难度
     */
    private Integer difficult;




}
