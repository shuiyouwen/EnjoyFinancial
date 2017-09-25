package com.example.shui.enjoyfinancial.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.feature.mian.MainActivity;
import com.example.shui.enjoyfinancial.network.ResultSubject;
import com.example.shui.enjoyfinancial.network.RetrofitClient;
import com.example.shui.enjoyfinancial.network.bean.resp.AdResp;
import com.example.shui.enjoyfinancial.network.helper.RxResultHelper;
import com.example.shui.enjoyfinancial.network.helper.RxSchedulersHelper;
import com.example.shui.enjoyfinancial.utils.GetConfiguration;
import com.example.shui.enjoyfinancial.utils.StatusBarUtil;
import com.example.shui.enjoyfinancial.utils.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * 闪屏界面
 * Created by Shui on 2017/9/6.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    private Unbinder mBind;
    private long mStartTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mStartTime = System.currentTimeMillis();
        setContentView(R.layout.activity_splash);
        mBind = ButterKnife.bind(this);
        //读取配置文件
        GetConfiguration.init(this);
        getPicture();
    }

    @Override
    protected void initStateBar() {
        StatusBarUtil.stateBarSetting(this, null, false);
    }

    private void getPicture() {
        RetrofitClient.getApi().adList("START")
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .compose(RxResultHelper.handleResult())
                .subscribe(new ResultSubject<List<AdResp>>(this) {
                    @Override
                    public void onNext(List<AdResp> response) {
                        Utils.loadImage(response.get(0).getImg_url(), mIvPicture, 0, 0);
                        goMain();
                    }

                    @Override
                    public void onError(Throwable e) {
                        goMain();
                    }
                });
    }

    /**
     * 跳转到主界面
     */
    private void goMain() {
        long time = 5000 - (System.currentTimeMillis() - mStartTime);
        time = time > 0 ? time : 0;
        Observable.timer(time, TimeUnit.MILLISECONDS)
                .compose(RxSchedulersHelper.ioMain())
                .subscribe(aLong -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                });
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

    @Override
    public void openLoading() {
    }

    @Override
    public void closeLoading() {
    }
}

