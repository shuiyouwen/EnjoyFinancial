package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * 系统消息
 * Created by Shui on 2017/9/12.
 */

public class MessageSystemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MessageSystemAdapter(@Nullable List<String> data) {
        super(R.layout.item_system_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
