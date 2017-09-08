package com.example.shui.enjoyfinancial.feature.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 手机号验证
 * Created by Shui on 2017/9/8.
 */

public class PhoneVerifyActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @Override
    protected int getPageTitle() {
        return R.string.phone_verify;
    }

    @OnClick({R.id.btn_get_code, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                break;
            case R.id.btn_next:
                startActivity(new Intent(this, PayPwdActivity.class));
                break;
        }
    }
}
