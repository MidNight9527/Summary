package com.example.springboottest2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "history")
@Data
public class History {
    /**
     * 历史id
     */
    @ApiModelProperty(value = "历史id")
    private Integer historyid;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer historyuserid;

    /**
     * 原文
     */
    @ApiModelProperty(value = "原文")
    private String originaltext;

    /**
     * 摘要
     */
    @ApiModelProperty(value = "摘要")
    private String summarytext;
}