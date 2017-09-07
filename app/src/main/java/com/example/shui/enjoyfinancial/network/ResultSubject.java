package com.example.shui.enjoyfinancial.network;

import android.util.Log;

import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.base.IBaseView;
import com.example.shui.enjoyfinancial.network.exception.ServerException;
import com.example.shui.enjoyfinancial.network.exception.TokenException;
import com.example.shui.enjoyfinancial.utils.Utils;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;

/**
 * 网络请求返回结果的处理的基本封装
 * Created by Mc丶水水 on 2017/3/28 0028.
 */

public abstract class ResultSubject<T> extends Subject<T> {
    private final IBaseView mBaseView;

    public ResultSubject(IBaseView baseView) {
        mBaseView = baseView;
        if (mBaseView != null) {
            mBaseView.openLoading();
        }
    }

    public ResultSubject(IBaseView baseView, boolean openLoading) {
        mBaseView = baseView;
        if (openLoading && mBaseView != null) {
            mBaseView.openLoading();
        }
    }

    @Override
    public boolean hasObservers() {
        return false;
    }

    @Override
    public boolean hasThrowable() {
        return false;
    }

    @Override
    public boolean hasComplete() {
        return false;
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }

    @Override
    protected void subscribeActual(Observer observer) {

    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e) {
        if (mBaseView == null) return;
        mBaseView.closeLoading();
        if (e instanceof ServerException) {
            //服务器异常
            mBaseView.showError(e.getMessage());
        } else if (e instanceof TokenException) {
            //登录状态失效
            mBaseView.launchLogin(true, e.getMessage());
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof UnknownHostException || e instanceof SSLException || e instanceof SocketException) {
            // 网络错误
            mBaseView.netError(e);
        } else {
            //其它异常
            if (mBaseView instanceof BaseFragment) {
                Utils.showToast(((BaseFragment) mBaseView).getActivity(), "未知错误：" + e.getMessage());
                Log.e("TAG", "未知错误：" + e.getMessage());
            } else {
                if (mBaseView instanceof BaseActivity) {
                    Utils.showToast((BaseActivity) mBaseView, "未知错误：" + e.getMessage());
                    Log.e("TAG", "未知错误：" + e.getMessage());
                }
            }
        }
    }

    @Override
    public void onComplete() {
        if (mBaseView != null) {
            mBaseView.closeLoading();
        }
    }
}
