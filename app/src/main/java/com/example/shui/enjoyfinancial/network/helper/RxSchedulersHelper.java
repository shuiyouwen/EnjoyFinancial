package com.example.shui.enjoyfinancial.network.helper;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求，子线程请求网络，主线程刷新UI
 * Created by Shui on 16/10/28.
 */

public class RxSchedulersHelper {
    public static <T> ObservableTransformer<T, T> ioMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
