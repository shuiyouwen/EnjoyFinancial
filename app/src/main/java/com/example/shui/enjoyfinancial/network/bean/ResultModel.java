package com.example.shui.enjoyfinancial.network.bean;

/**
 * @author: Mr.Shui
 * @time: 16/11/6 18:27
 * @desc: 通用后台返回结果的包装实体类
 */

public class ResultModel<T> {
    public static final String RESP_SUCCESS = "000";
    public static final String RESP_TOKEN_ERROR = "104";

    private String code;
    private String message;
    private T data;

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
