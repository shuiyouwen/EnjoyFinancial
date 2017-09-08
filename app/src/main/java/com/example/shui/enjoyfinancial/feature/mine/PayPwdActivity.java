package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 支付密码验证
 * Created by Shui on 2017/9/8.
 */

public class PayPwdActivity extends BaseActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pwd);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @Override
    protected int getPageTitle() {
        return R.string.pay_pwd_verify;
    }
}
