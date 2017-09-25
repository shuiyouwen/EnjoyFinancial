package com.example.shui.enjoyfinancial.feature.category;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.CategoryListAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.feature.product.ProductActivity;
import com.example.shui.enjoyfinancial.network.ResultSubject;
import com.example.shui.enjoyfinancial.network.RetrofitClient;
import com.example.shui.enjoyfinancial.network.bean.resp.CategoryResp;
import com.example.shui.enjoyfinancial.network.bean.resp.ProductResp;
import com.example.shui.enjoyfinancial.network.helper.RxResultHelper;
import com.example.shui.enjoyfinancial.network.helper.RxSchedulersHelper;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.List;

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
    @BindView(R.id.iv_arrow)
    ImageView mIvArrow;

    //点击id对应的textview
    private SparseArray<TextView> mSelectTvSparse = new SparseArray<>();
    private CategoryListAdapter mAdapter;
    private int mPage = 1;
    private String mSort = "0";

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
        productList(false);
        assert mSwipeRefreshLayout != null;
        mSwipeRefreshLayout.setOnRefreshListener(() -> productList(false));
        mAdapter = new CategoryListAdapter(null);
        mRvProduct.setAdapter(mAdapter);
        mRvProduct.setLayoutManager(new LinearLayoutManager(mActivity));
        RecyclerView.ItemDecoration itemDecoration = new RVItemDecoration(Color.TRANSPARENT, 10);
        mRvProduct.addItemDecoration(itemDecoration);
        mSelectTvSparse.append(R.id.tv_category, mTvCategory);
        mSelectTvSparse.append(R.id.tv_recommend, mTvRecommend);
        mSelectTvSparse.append(R.id.fl_price, mTvPrice);
        selectTextView(R.id.tv_category);
        mAdapter.setOnItemClickListener((adapter1, view, position) ->
                startActivity(new Intent(mActivity, ProductActivity.class))
        );
        mAdapter.setOnLoadMoreListener(() -> productList(true), mRvProduct);
    }

    private void productList(boolean isLoadMore) {
        mPage = isLoadMore ? (mPage + 1) : 1;
        RetrofitClient.getApi().goodsList("", "", "0", String.valueOf(mPage))
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .compose(RxResultHelper.handleResult())
                .subscribe(new ResultSubject<List<ProductResp>>(this, !isLoadMore) {
                    @Override
                    public void onNext(List<ProductResp> response) {
                        if (isLoadMore) {
                            if (response.size() == 0) {
                                mAdapter.loadMoreEnd();
                            } else {
                                mAdapter.addData(response);
                                mAdapter.loadMoreComplete();
                            }
                        } else {
                            mAdapter.setNewData(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isLoadMore) {
                            mAdapter.loadMoreFail();
                        } else {
                            super.onError(e);
                        }
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
        switch (view.getId()) {
            case R.id.fl_price:
                if (TextUtils.equals(mSort, "1")) {
                    //如果当前状态是升序，切换到降序
                    mSort = "2";
                    mIvArrow.setImageResource(R.mipmap.yello_down);
                } else {
                    mSort = "1";
                    mIvArrow.setImageResource(R.mipmap.yello_up);
                }
                productList(false);
                break;
            case R.id.tv_category:
                getCategory();
                break;
        }
    }

    /**
     * 获取分类列表
     */
    private void getCategory() {
        RetrofitClient.getApi().goodsType("")
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .compose(RxResultHelper.handleResult())
                .subscribe(new ResultSubject<List<CategoryResp>>(this) {
                    @Override
                    public void onNext(List<CategoryResp> response) {

                    }
                });
    }

    private void selectTextView(int resId) {
        mTvCategory.setTextColor(ContextCompat.getColor(mActivity, R.color.font_black_33));
        mTvRecommend.setTextColor(ContextCompat.getColor(mActivity, R.color.font_black_33));
        mTvPrice.setTextColor(ContextCompat.getColor(mActivity, R.color.font_black_33));
        mSelectTvSparse.get(resId).setTextColor(ContextCompat.getColor(mActivity, R.color.font_orange_cf));
    }
}
