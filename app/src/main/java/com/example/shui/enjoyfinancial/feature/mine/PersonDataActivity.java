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
 * 个人中心
 * Created by Shui on 2017/9/8.
 */

public class PersonDataActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_data);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @Override
    protected int getPageTitle() {
        return R.string.person_data;
    }

    @OnClick(R.id.tv_edit)
    public void onViewClicked() {
    }
}
