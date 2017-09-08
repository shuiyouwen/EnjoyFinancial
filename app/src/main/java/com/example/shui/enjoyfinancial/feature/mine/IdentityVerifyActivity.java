package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 身份验证
 * Created by Shui on 2017/9/8.
 */

public class IdentityVerifyActivity extends BaseActivity {
    @BindView(R.id.et_identity)
    EditText mEtIdentity;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_verify);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected int getPageTitle() {
        return R.string.identity_verify;
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
    }
}
