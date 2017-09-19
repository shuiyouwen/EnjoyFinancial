package com.example.shui.enjoyfinancial.feature.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.CouponListAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 优惠券列表
 * Created by Shui on 2017/9/13.
 */

public class CouponListFragment extends BaseFragment {
    @BindView(R.id.rv_coupon)
    RecyclerView mRvCoupon;
    Unbinder unbinder;

    public static CouponListFragment newInstance() {

        Bundle args = new Bundle();

        CouponListFragment fragment = new CouponListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_coupon_list, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        List<String> data = Arrays.asList("1", "2", "3", "4");
//        List<String> data = new ArrayList<>();
        CouponListAdapter adapter = new CouponListAdapter(data);
        mRvCoupon.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRvCoupon.setLayoutManager(layoutManager);
        RVItemDecoration itemDecoration = new RVItemDecoration(Color.TRANSPARENT, 13);
        mRvCoupon.addItemDecoration(itemDecoration);

        View inflate = View.inflate(mActivity, R.layout.empty_coupon, null);
        adapter.setEmptyView(inflate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
