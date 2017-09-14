package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * 还款详情
 * Created by Shui on 2017/9/14.
 */

public class RepaymentDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RepaymentDetailAdapter(@Nullable List<String> data) {
        super(R.layout.item_repayment_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
