package com.example.shui.enjoyfinancial.network.exception;

/**
 * 服务器异常
 * Created by Administrator on 2016/11/8 0008.
 */
public class ServerException extends Exception {
    private String code;
    private String msg;

    public ServerException(String msg,String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
