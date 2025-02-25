package com.example.stumanager.common;

import java.io.Serializable;

/**
* 封装返回结果
*/
public class Result implements Serializable{
    public static final Integer SUCCESS = 200; // 成功的状态码
    public static final Integer FAILURE = 200; // 失败的状态码
    private Integer code;//执行结果码，200表示成功，5XX表示服务器错误
    private String message;//返回结果信息，主要用于页面提示信息
    private Object data;//返回数据
    public Result() {

    }
    public Result(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
            "code=" + code +
            ", message='" + message + '\'' +
            ", data=" + data +
            '}';
    }
}
