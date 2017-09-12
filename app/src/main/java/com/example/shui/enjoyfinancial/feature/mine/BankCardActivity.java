package com.example.shui.enjoyfinancial.feature.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.BankCardAdapter;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 银行卡
 * Created by Shui on 2017/9/12.
 */

public class BankCardActivity extends BaseActivity {
    @BindView(R.id.rv_bank_card)
    RecyclerView mRvBankCard;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        mBind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getPageTitle() {
        return R.string.bank_card;
    }

    private void initView() {
        List<String> data = Arrays.asList("1", "2", "3", "4", "5", "6");
        BankCardAdapter adapter = new BankCardAdapter(data);
        mRvBankCard.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvBankCard.setLayoutManager(layoutManager);
        RVItemDecoration itemDecoration = new RVItemDecoration(Color.TRANSPARENT, 14);
        mRvBankCard.addItemDecoration(itemDecoration);
        View inflate = View.inflate(this, R.layout.empty_bankcard, null);
        inflate.findViewById(R.id.tv_add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        adapter.setEmptyView(inflate);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @OnClick(R.id.iv_add)
    public void onViewClicked() {
    }
}
