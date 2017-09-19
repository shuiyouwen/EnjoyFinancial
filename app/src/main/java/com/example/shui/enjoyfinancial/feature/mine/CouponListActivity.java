package com.example.shui.enjoyfinancial.feature.mine;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.BillAdapter;
import com.example.shui.enjoyfinancial.adapter.CouponAdapter;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 优惠券查看
 * Created by Shui on 2017/9/12.
 */

public class CouponListActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        mBind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mViewPager.setAdapter(new CouponAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(ContextCompat.getColor(this, R.color.gray_dc));
        drawable.setSize(1, 1);
        linearLayout.setDividerDrawable(drawable);
        linearLayout.setDividerPadding(Utils.dip2px(this, 7));
    }

    @Override
    protected int getPageTitle() {
        return R.string.coupon;
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }
}
