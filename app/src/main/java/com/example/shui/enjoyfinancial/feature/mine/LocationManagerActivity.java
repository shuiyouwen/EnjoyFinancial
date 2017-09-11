package com.example.shui.enjoyfinancial.feature.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.LocationAdapter;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 地址管理
 * Created by Shui on 2017/9/8.
 */

public class LocationManagerActivity extends BaseActivity {
    @BindView(R.id.rv_location)
    RecyclerView mRvLocation;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_manager);
        mBind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getPageTitle() {
        return R.string.location_manager;
    }

    private void initView() {
//        List<String> data = Arrays.asList("1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3");
        List<String> data = new ArrayList<>();
        LocationAdapter adapter = new LocationAdapter(data);
        mRvLocation.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvLocation.setLayoutManager(layoutManager);
        RVItemDecoration itemDecoration = new RVItemDecoration(Color.TRANSPARENT, 10);
        mRvLocation.addItemDecoration(itemDecoration);

        View inflate = View.inflate(this, R.layout.empty_location, null);
        inflate.findViewById(R.id.tv_add_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationManagerActivity.this, AddAddressActivity.class));
            }
        });
        adapter.setEmptyView(inflate);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }
}
