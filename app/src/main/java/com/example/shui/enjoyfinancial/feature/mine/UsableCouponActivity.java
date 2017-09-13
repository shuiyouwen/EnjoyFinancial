package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 可用优惠券
 * Created by Shui on 2017/9/12.
 */

public class UsableCouponActivity extends BaseActivity {
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usable_coupon);
        mBind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_container, CouponListFragment.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    protected int getPageTitle() {
        return R.string.usable_coupon;
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }
}
