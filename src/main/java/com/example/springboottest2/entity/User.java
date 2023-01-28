package com.example.springboottest2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "`user`")
@Data
public class User {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userid;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
}