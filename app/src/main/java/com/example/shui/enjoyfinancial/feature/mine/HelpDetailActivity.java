package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 帮助详情
 * Created by Shui on 2017/9/13.
 */

public class HelpDetailActivity extends BaseActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @Override
    protected int getPageTitle() {
        return R.string.help_center;
    }

    @OnClick({R.id.tv_solve, R.id.tv_not_solve})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_solve:
                break;
            case R.id.tv_not_solve:
                break;
        }
    }
}
