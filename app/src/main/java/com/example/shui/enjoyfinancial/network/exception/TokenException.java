package com.example.shui.enjoyfinancial.network.exception;

/**
 * token失效异常
 * Created by Administrator on 2016/11/8 0008.
 */

public class TokenException extends Exception {
    private String errorMsg;
    private String errorCode;

    public TokenException(String errorMsg, String errorCode) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
