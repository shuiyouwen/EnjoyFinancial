package com.example.shui.enjoyfinancial.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.constants.TagConstants;
import com.example.shui.enjoyfinancial.network.bean.resp.CategoryResp;
import com.hwangjr.rxbus.RxBus;

import java.util.List;

import static com.example.shui.enjoyfinancial.utils.Utils.dip2px;

/**
 * 商品分类选择弹窗
 * Created by Shui on 2017/9/26.
 */

public class ProductCategoryDialog extends AlertDialog {

    private final List<CategoryResp> mCategoryResps;
    private final Context mContext;
    private LinearLayout mLinearLayout;
    private View mSelectView;

    public ProductCategoryDialog(@NonNull Context context, List<CategoryResp> categoryResps) {
        super(context, R.style.TransparentDialog);
        mCategoryResps = categoryResps;
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_product_category);

        mLinearLayout = (LinearLayout) findViewById(R.id.ll_container);
        for (CategoryResp categoryResp : mCategoryResps) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;

            FrameLayout frameLayout = new FrameLayout(mContext);
            frameLayout.setLayoutParams(layoutParams);
            assert mLinearLayout != null;
            mLinearLayout.addView(frameLayout);

            FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            fLayoutParams.gravity = Gravity.CENTER;
            TextView textView = new TextView(mContext);
            textView.setPadding(dip2px(mContext, 10), dip2px(mContext, 3),
                    dip2px(mContext, 10), dip2px(mContext, 3));
            textView.setLayoutParams(fLayoutParams);
            textView.setText(categoryResp.getName());
            textView.setTextSize(16);
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.font_black_33));
            textView.setGravity(Gravity.CENTER);
            if (categoryResp.isSelected()) {
                selected(textView);
            } else {
                normal(textView);
            }

            frameLayout.setOnClickListener(v -> {
                if (mSelectView == v) return;

                mSelectView = v;
                resetViewState();
                selected(textView);
                RxBus.get().post(TagConstants.CATEGORY_CHECK, categoryResp.getId());
                cancel();
            });
            frameLayout.addView(textView);

            if (TextUtils.equals(categoryResp.getName(), "全部")) {
                mSelectView = frameLayout;
            }
        }
    }

    private void normal(TextView textView) {
        textView.setBackgroundResource(android.R.color.white);
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.font_black_33));
    }

    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.shape_orange_rectangle_14);
        textView.setTextColor(Color.WHITE);
    }

    private void resetViewState() {
        int childCount = mLinearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView view = (TextView) ((FrameLayout) mLinearLayout.getChildAt(i)).getChildAt(0);
            normal(view);
        }
    }

    @Override
    public void show() {
        super.show();

        //设置全屏
        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(layoutParams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
