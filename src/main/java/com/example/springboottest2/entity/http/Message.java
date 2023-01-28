package com.example.springboottest2.entity.http;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

public class Message<T> {

    public interface UnionSimpleView {}

    @ApiModelProperty(value = "请求结果")
    @JsonView(UnionSimpleView.class)
    private boolean result;

    @ApiModelProperty(value = "补充信息")
    @JsonView(UnionSimpleView.class)
    private  String msg;

    @ApiModelProperty(value = "包含数据")
    @JsonView(UnionSimpleView.class)
    private T obj;

    private Message(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public static <T> Message<T> success(String str){
        return new Message<T>(true,str==null?"操作成功":str);
    }

    public static <T> Message<T> fail(String str){
        return new Message<T>(false,str==null?"操作失败":str);
    }

    //添加包含的数据
    public Message add(T obj)
    {
        this.setObj(obj);
        return this;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
