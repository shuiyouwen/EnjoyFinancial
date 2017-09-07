package com.example.shui.enjoyfinancial.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.utils.Utils;

import java.util.List;

import static com.example.shui.enjoyfinancial.utils.Utils.dip2px;
import static com.example.shui.enjoyfinancial.utils.Utils.strikethroughTextView;

/**
 * 分类列表adapter
 * Created by Shui on 2017/9/7.
 */

public class CategoryListAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    private final Activity mActivity;

    public CategoryListAdapter(@Nullable List<Object> data, Activity activity) {
        super(R.layout.item_category_list, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        TextView tvOriginalPrice = helper.getView(R.id.tv_original_price);
        TextView tvPrice = helper.getView(R.id.tv_price);
        strikethroughTextView(tvOriginalPrice);
        setPrice("5899", tvPrice);
    }

    private void setPrice(String price, TextView textView) {
        price = String.format("￥%s", price);
        SpannableStringBuilder builder = new SpannableStringBuilder(price);
        AbsoluteSizeSpan sizeSpan1 = new AbsoluteSizeSpan(dip2px(mActivity, 12));
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.font_gray_6b));
        builder.setSpan(sizeSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(colorSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }
}
