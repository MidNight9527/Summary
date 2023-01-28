package com.example.springboottest2.entity.http;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class Resp<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 网络状态码
     */
    @ApiModelProperty(value = "网络状态码")
    private Integer httpCode;

    /**
     * 自定义状态码
     */
    @ApiModelProperty(value = "自定义状态码")
    private Integer code;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String msg;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private T data;


    public Resp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Resp(Integer httpCode, Integer code, String msg) {
        this.httpCode = httpCode;
        this.code = code;
        this.msg = msg;
    }

    public Resp(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp(Integer httpCode, Integer code, String msg, T data) {
        this.httpCode = httpCode;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //快速返回成功
    public static <T> Resp success() {
        return new Resp<T>(HttpStatus.OK.value(), ResponseCode.SUCCESS.getValue(), ResponseCode.SUCCESS.getmsg(), null);
    }

    public static <T> Resp success(T result) {
        return new Resp<T>(HttpStatus.OK.value(), ResponseCode.SUCCESS.getValue(), ResponseCode.SUCCESS.getmsg(), result);
    }

    public static <T> Resp success(T result,String msg) {
        return new Resp<T>(HttpStatus.OK.value(), ResponseCode.SUCCESS.getValue(), msg, result);
    }

    public static <T> Resp success(Integer code, String msg, T result) {
        return new Resp<T>(HttpStatus.OK.value(), code, msg, result);
    }

    public static <T> Resp success(Integer httpCode, Integer code, String msg, T result) {
        return new Resp<T>(httpCode, code, msg, result);
    }

    //快速返回失败状态
    public static <T> Resp fail() {
        return new Resp<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseCode.FAIL.getValue(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    public static <T> Resp fail(String msg) {
        return new Resp<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> Resp fail(Integer code) {
        return new Resp<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), code, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    public static <T> Resp fail(Integer code, String msg) {
        return new Resp<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), code, msg);
    }

    public static <T> Resp fail(Integer code, String msg, T result) {
        return new Resp<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), code, msg, result);
    }

    public <T> Resp fail(Integer httpCode, Integer code, String msg, T result) {
        return new Resp<T>(httpCode, code, msg, result);
    }
}
