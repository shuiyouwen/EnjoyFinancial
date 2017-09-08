package com.example.shui.enjoyfinancial.feature.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 安全中心
 * Created by Shui on 2017/9/8.
 */

public class SafeCenterActivity extends BaseActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_center);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @Override
    protected int getPageTitle() {
        return R.string.safe_center;
    }

    @OnClick({R.id.fl_phone_bind, R.id.fl_pay_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_phone_bind:
                startActivity(new Intent(this, PhoneVerifyActivity.class));
                break;
            case R.id.fl_pay_pwd:
                startActivity(new Intent(this, IdentityVerifyActivity.class));
                break;
        }
    }
}
