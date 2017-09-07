package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.List;

/**
 * 分类列表adapter
 * Created by Shui on 2017/9/7.
 */

public class CategoryListAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public CategoryListAdapter(@Nullable List<Object> data) {
        super(R.layout.item_category_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
