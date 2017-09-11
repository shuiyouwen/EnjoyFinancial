package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * Created by Shui on 2017/9/11.
 */

public class LocationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public LocationAdapter(@Nullable List<String> data) {
        super(R.layout.item_location, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
