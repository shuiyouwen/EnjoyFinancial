package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * Created by Shui on 2017/9/11.
 */

public class OrderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public OrderAdapter(@Nullable List<String> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
