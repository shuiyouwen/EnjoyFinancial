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
 * 设置中心
 * Created by Shui on 2017/9/8.
 */

public class SettingActivity extends BaseActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected int getPageTitle() {
        return R.string.setting_center;
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.ll_person_center, R.id.ll_safe_center, R.id.ll_location_manager, R.id.ll_about_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_person_center:
                startActivity(new Intent(this, PersonDataActivity.class));
                break;
            case R.id.ll_safe_center:
                startActivity(new Intent(this, SafeCenterActivity.class));
                break;
            case R.id.ll_location_manager:
                startActivity(new Intent(this, LocationManagerActivity.class));
                break;
            case R.id.ll_about_me:
                startActivity(new Intent(this, AboutMeActivity.class));
                break;
        }
    }
}
