package com.example.shui.enjoyfinancial.feature.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.OrderAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的界面
 * Created by Shui on 2017/9/6.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    Unbinder unbinder;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    protected int getPageTitle() {
        return R.string.mine;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_setting, R.id.iv_message, R.id.fl_apply_limit, R.id.fl_bank_card, R.id.fl_discount_coupon, R.id.fl_bill, R.id.fl_order, R.id.fl_help_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.iv_message:
                break;
            case R.id.fl_apply_limit:
                break;
            case R.id.fl_bank_card:
                break;
            case R.id.fl_discount_coupon:
                break;
            case R.id.fl_bill:
                break;
            case R.id.fl_order:
                startActivity(new Intent(mActivity, OrderActivity.class));
                break;
            case R.id.fl_help_center:
                break;
        }
    }
}
