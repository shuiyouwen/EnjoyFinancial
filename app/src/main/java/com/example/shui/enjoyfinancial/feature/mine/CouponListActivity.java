package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 优惠券查看
 * Created by Shui on 2017/9/12.
 */

public class CouponListActivity extends BaseActivity {
    @BindView(R.id.tv_can_use)
    TextView mTvCanUse;
    @BindView(R.id.v_line_can_use)
    View mVLineCanUse;
    @BindView(R.id.tv_history)
    TextView mTvHistory;
    @BindView(R.id.v_line_history)
    View mVLineHistory;

    private Unbinder mBind;
    private SparseArray<BaseFragment> mFragmentSparseArray = new SparseArray<>();
    private CouponListFragment mCanUseCouponFragment = CouponListFragment.newInstance();
    private CouponListFragment mHistoryCouponFragment = CouponListFragment.newInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        mBind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        selectCanUse();

        mFragmentSparseArray.put(R.id.tv_can_use, mCanUseCouponFragment);
        mFragmentSparseArray.put(R.id.tv_history, mHistoryCouponFragment);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_container, mCanUseCouponFragment)
                .add(R.id.fl_container, mHistoryCouponFragment)
                .hide(mHistoryCouponFragment)
                .show(mCanUseCouponFragment)
                .commit();
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

    @OnClick({R.id.tv_can_use, R.id.tv_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_can_use:
                selectCanUse();
                break;
            case R.id.tv_history:
                selectHistory();
                break;
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mCanUseCouponFragment)
                .hide(mHistoryCouponFragment)
                .show(mFragmentSparseArray.get(view.getId()))
                .commit();
    }

    private void selectCanUse() {
        mTvCanUse.setTextColor(ContextCompat.getColor(this, R.color.font_orange_cf));
        mTvHistory.setTextColor(ContextCompat.getColor(this, R.color.font_black_33));
        mVLineCanUse.setVisibility(View.VISIBLE);
        mVLineHistory.setVisibility(View.INVISIBLE);
    }

    private void selectHistory() {
        mTvHistory.setTextColor(ContextCompat.getColor(this, R.color.font_orange_cf));
        mTvCanUse.setTextColor(ContextCompat.getColor(this, R.color.font_black_33));
        mVLineCanUse.setVisibility(View.INVISIBLE);
        mVLineHistory.setVisibility(View.VISIBLE);
    }

}
