package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.RepaymentPlanAdapter;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 支付订单
 * Created by Shui on 2017/9/11.
 */

public class PayOrderActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.rv_repayment_plan)
    RecyclerView mRvRepaymentPlan;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        mBind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<String> data = Arrays.asList("1", "2", "3", "4");
        RepaymentPlanAdapter adapter = new RepaymentPlanAdapter(data);
        mRvRepaymentPlan.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvRepaymentPlan.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @Override
    protected int getPageTitle() {
        return R.string.my_order;
    }
}
