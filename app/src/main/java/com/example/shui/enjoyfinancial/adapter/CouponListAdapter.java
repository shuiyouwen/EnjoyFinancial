package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * 优惠券列表
 * Created by Shui on 2017/9/12.
 */

public class CouponListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CouponListAdapter(@Nullable List<String> data) {
        super(R.layout.item_counpon, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
