package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * Created by Shui on 2017/9/12.
 */

public class BankCardAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public BankCardAdapter( @Nullable List<String> data) {
        super(R.layout.item_bank_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
