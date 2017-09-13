package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * 账单列表
 * Created by Shui on 2017/9/13.
 */

public class BillListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public BillListAdapter(@Nullable List<String> data) {
        super(R.layout.item_bill, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
