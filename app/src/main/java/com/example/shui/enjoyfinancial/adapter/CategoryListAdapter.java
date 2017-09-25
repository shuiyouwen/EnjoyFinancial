package com.example.shui.enjoyfinancial.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.network.bean.resp.ProductResp;

import java.util.List;

import static com.example.shui.enjoyfinancial.utils.Utils.formatAmt;
import static com.example.shui.enjoyfinancial.utils.Utils.loadImage;
import static com.example.shui.enjoyfinancial.utils.Utils.setPrice;
import static com.example.shui.enjoyfinancial.utils.Utils.strikethroughTextView;

/**
 * 分类列表adapter
 * Created by Shui on 2017/9/7.
 */

public class CategoryListAdapter extends BaseQuickAdapter<ProductResp, BaseViewHolder> {

    public CategoryListAdapter(@Nullable List<ProductResp> data) {
        super(R.layout.item_category_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductResp item) {
        helper.setText(R.id.tv_product, item.getTitle());

        TextView tvOriginalPrice = helper.getView(R.id.tv_original_price);
        strikethroughTextView(tvOriginalPrice);
        tvOriginalPrice.setText(String.format("￥%s", formatAmt(item.getPrice_min())));

        TextView tvPrice = helper.getView(R.id.tv_price);
        setPrice(formatAmt(item.getPreferent_min()), tvPrice, 12, R.color.font_gray_6b);

        String monthPrice = item.isSupportStages() ?
                String.format("￥%s/月 起", formatAmt(item.getMonthly_min())) : "暂不支持";
        helper.setText(R.id.tv_month_pay, monthPrice);

        ImageView ivProduct = helper.getView(R.id.iv_product);
        loadImage(item.getImg_url(), ivProduct, 0, 0);
    }
}
