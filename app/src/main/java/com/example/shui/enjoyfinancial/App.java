package com.example.shui.enjoyfinancial;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * Created by Shui on 2017/9/5.
 */

public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context sContext;
    private Disposable mSubscribe;

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
        sContext = getApplicationContext();
    }
}
