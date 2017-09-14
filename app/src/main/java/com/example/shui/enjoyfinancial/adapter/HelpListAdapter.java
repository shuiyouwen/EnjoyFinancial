package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * Created by Shui on 2017/9/13.
 */

public class HelpListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HelpListAdapter( @Nullable List<String> data) {
        super(R.layout.item_help, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
