package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * 还款计划
 * Created by Shui on 2017/9/11.
 */

public class RepaymentPlanAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RepaymentPlanAdapter(@Nullable List<String> data) {
        super(R.layout.item_repayment_plan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
