package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情adapter
 * Created by Shui on 2017/9/8.
 */

public class ProductAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<TextView> mTextViews = new ArrayList<>();

    public ProductAdapter(@Nullable List<String> data) {
        super(R.layout.item_product, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tvName = helper.getView(R.id.tv_name);
        tvName.setText(item);
        helper.addOnClickListener(R.id.tv_name);
        mTextViews.add(tvName);
    }

    public void resetAllView() {
        for (TextView textView : mTextViews) {
            textView.setActivated(false);
        }
    }
}
