package com.example.shui.enjoyfinancial.feature.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.widget.dialog.VerifyCodeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 登录界面
 * Created by Shui on 2017/9/5.
 */

public class LoginActivity extends BaseActivity {
    public static final int RESULT_LOGIN_SUCCESS = 101;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;

    private Unbinder mBind;
    private CountDownTimer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        if (mTimer != null) {
            mTimer.cancel();
        }
        super.onDestroy();
    }

    @OnClick({R.id.btn_get_code, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                mTimer = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mBtnGetCode.setEnabled(false);
                        mBtnGetCode.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    @Override
                    public void onFinish() {
                        mBtnGetCode.setEnabled(true);
                        mBtnGetCode.setText(R.string.get_sms_code);
                    }
                };
                mTimer.start();
                break;
            case R.id.btn_login:
                VerifyCodeDialog dialog = new VerifyCodeDialog(this);
                dialog.show();
                break;
        }
    }
}
