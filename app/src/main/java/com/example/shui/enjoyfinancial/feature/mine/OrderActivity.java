package com.example.shui.enjoyfinancial.feature.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.OrderAdapter;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的订单
 * Created by Shui on 2017/9/11.
 */

public class OrderActivity extends BaseActivity {
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mBind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<String> data = Arrays.asList("1", "2", "3", "1", "2", "3", "1", "2", "3");
//        List<String> data = new ArrayList<>();
        OrderAdapter adapter = new OrderAdapter(data);
        mRvOrder.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvOrder.setLayoutManager(layoutManager);
        RVItemDecoration itemDecoration = new RVItemDecoration(Color.TRANSPARENT, 10);
        mRvOrder.addItemDecoration(itemDecoration);

        View inflate = View.inflate(this, R.layout.empty_order, null);
        inflate.findViewById(R.id.tv_stroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderActivity.this, "去逛逛", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setEmptyView(inflate);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(OrderActivity.this, SubmitOrderActivity.class));
            }
        });
    }

    @Override
    protected int getPageTitle() {
        return R.string.my_order;
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }


}
