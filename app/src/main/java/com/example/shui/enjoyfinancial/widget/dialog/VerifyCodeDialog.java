package com.example.shui.enjoyfinancial.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.shui.enjoyfinancial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 验证码弹窗
 * Created by Shui on 2017/9/5.
 */

public class VerifyCodeDialog extends AlertDialog {

    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.iv_verify_code)
    ImageView mIvVerifyCode;
    private Unbinder mBind;

    public VerifyCodeDialog(Context context) {
        super(context, false, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_verify_code);
        mBind = ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new BitmapDrawable());
    }

    @Override
    public void onDetachedFromWindow() {
        mBind.unbind();
        super.onDetachedFromWindow();
    }

    @OnClick({R.id.btn_cancel, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                cancel();
                break;
            case R.id.btn_submit:
                break;
        }
    }
}
