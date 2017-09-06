package com.example.shui.enjoyfinancial.base;

/**
 * View的基类
 * Created by Shui on 2017/7/5.
 */

public interface IBaseView {
    void openLoading();//启动网络请求进度提示

    void closeLoading();//关闭网络请求进度提示

    void showError(String errorMsg);//显示错误提示

    void launchLogin(boolean isShowMsg, String msg);//启动登录界面

    void netError(Throwable e);//网络错误

    void loginBack();//登录之后回来
}
