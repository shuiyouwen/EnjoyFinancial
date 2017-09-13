package com.example.shui.enjoyfinancial.feature.category;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.CategoryListAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.feature.product.ProductActivity;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 分类界面
 * Created by Shui on 2017/9/6.
 */

public class CategoryFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    Unbinder unbinder;
    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.tv_recommend)
    TextView mTvRecommend;
    @BindView(R.id.tv_price)
    TextView mTvPrice;

    //点击id对应的textview
    private SparseArray<TextView> mSelectTvSparse = new SparseArray<>();

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        List<Object> objects = Arrays.asList(new Object(), new Object(), new Object(), new Object(), new Object(), new Object());
        CategoryListAdapter adapter = new CategoryListAdapter(objects, mActivity);
        mRvProduct.setAdapter(adapter);
        mRvProduct.setLayoutManager(new LinearLayoutManager(mActivity));
        RecyclerView.ItemDecoration itemDecoration = new RVItemDecoration(Color.TRANSPARENT, 10);
        mRvProduct.addItemDecoration(itemDecoration);
        mSelectTvSparse.append(R.id.tv_category, mTvCategory);
        mSelectTvSparse.append(R.id.tv_recommend, mTvRecommend);
        mSelectTvSparse.append(R.id.fl_price, mTvPrice);
        selectTextView(R.id.tv_category);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, ProductActivity.class));
            }
        });
    }

    @Override
    protected int getPageTitle() {
        return R.string.category;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_category, R.id.tv_recommend, R.id.fl_price})
    public void onViewClicked(View view) {
        selectTextView(view.getId());
    }

    private void selectTextView(int resId) {
        mTvCategory.setTextColor(ContextCompat.getColor(mActivity, R.color.font_black_33));
        mTvRecommend.setTextColor(ContextCompat.getColor(mActivity, R.color.font_black_33));
        mTvPrice.setTextColor(ContextCompat.getColor(mActivity, R.color.font_black_33));
        mSelectTvSparse.get(resId).setTextColor(ContextCompat.getColor(mActivity, R.color.font_orange_cf));
    }
}
