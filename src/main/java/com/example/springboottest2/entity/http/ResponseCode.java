package com.example.springboottest2.entity.http;

public enum ResponseCode {

    /**
     * 错误码暂定都是5位数字，并配有相应解释
     * 错误码按模块按功能场景分级分段，前三位错误码表示模块，第四位表示模块下的功能
     * 数字 1 开头的错误码表示系统级别的错误
     * 数字 4 开头的错误码表示API参数校验失败
     * 数字 5 开头的错误码表示后台业务校验失败
     */

    SUCCESS(0, "OK"),
    FAIL(-1, "ERROR"),

    SERVER_EXCEPTION(10000, "服务端发生未知异常"),
    DATA_NOT_EXIST(10001, "数据不存在"),
    PERMISSION_DENIED(1003, "无访问权限"),
    OPERATION_FAILED(1004, "操作失败"),

    INVALID_PARAM(40000, "参数错误"),
    HTTP_METHOD_NOT_SUPPORTED(40001, "Request method not supported"),
    INVALID_TIMESTAMP(40011, "invalid  timestamp"),
    INVALID_NONCE(40012, "invalid nonce string"),
    INVALID_SIGNATURE(40013, "invalid signature"),
    INCOMPLETE_PARAMETERS(40101, "参数不全"),
    INVALID_PARAMETER(40202, "参数不合法"),

    //用户模块501
    UN_AUTHENTICATION(50100, "用户未认证,请先登录"),
    EXPIRED(50101, "登录状态已失效,请重新登录"),
    INVALID_TOKEN(50102, "无效的accessToken"),
    USER_NOT_EXIST(50103, "用户不存在"),
    USER_EXIST(50104, "用户已存在，该手机号已注册"),
    USER_IS_DISABLED(50105, "用户已被禁用"),
    USER_PWD_ERROR(50106, "密码错误"),
    NO_PERMISSION(50107, "没有操作权限"),


    //文件模块502
    FILE_NOT_EXIST(50501, "文件不存在"),
    FILE_UPLOAD_FAILED(50502, "文件上传失败"),

    ;

    private final Integer value;
    private final String msg;

    ResponseCode(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public static ResponseCode getByCode(Integer value) {
        for (ResponseCode _enum : values()) {
            if (_enum.getValue() == value) {
                return _enum;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getmsg() {
        return msg;
    }

}
