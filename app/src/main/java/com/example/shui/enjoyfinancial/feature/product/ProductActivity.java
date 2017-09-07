package com.example.shui.enjoyfinancial.feature.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 商品详情activity
 * Created by Shui on 2017/9/7.
 */

public class ProductActivity extends BaseActivity {
    @BindView(R.id.v_line_product)
    View mVLineProduct;
    @BindView(R.id.v_line_detail)
    View mVLineDetail;
    @BindView(R.id.v_line_desc)
    View mVLineDesc;
    @BindView(R.id.tv_product)
    TextView mTvProduct;
    @BindView(R.id.tv_detail)
    TextView mTvDetail;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    private Unbinder mBind;

    private SparseArray<View> mLineSparse = new SparseArray<>();
    private SparseArray<TextView> mTextSparse = new SparseArray<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mBind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mLineSparse.append(R.id.fl_product, mVLineProduct);
        mLineSparse.append(R.id.fl_desc, mVLineDesc);
        mLineSparse.append(R.id.fl_detail, mVLineDetail);
        mTextSparse.append(R.id.fl_product, mTvProduct);
        mTextSparse.append(R.id.fl_desc, mTvDesc);
        mTextSparse.append(R.id.fl_detail, mTvDetail);
        setSelect(R.id.fl_product);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.fl_product, R.id.fl_detail, R.id.fl_desc})
    public void onViewClicked(View view) {
        setSelect(view.getId());
    }

    private void setSelect(int resId) {
        mVLineDesc.setVisibility(View.GONE);
        mVLineDetail.setVisibility(View.GONE);
        mVLineProduct.setVisibility(View.GONE);
        mLineSparse.get(resId).setVisibility(View.VISIBLE);

        mTvProduct.setTextColor(ContextCompat.getColor(this, R.color.font_black_33));
        mTvDesc.setTextColor(ContextCompat.getColor(this, R.color.font_black_33));
        mTvDetail.setTextColor(ContextCompat.getColor(this, R.color.font_black_33));
        mTextSparse.get(resId).setTextColor(ContextCompat.getColor(this, R.color.orange_cf));
    }
}
