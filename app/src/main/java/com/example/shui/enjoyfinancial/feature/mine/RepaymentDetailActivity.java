package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.RepaymentDetailAdapter;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;
import com.example.shui.enjoyfinancial.widget.dialog.AutoWithholdDialog;
import com.example.shui.enjoyfinancial.widget.dialog.CurrentPeriodRepaymentDialog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 还款详情
 * Created by Shui on 2017/9/14.
 */

public class RepaymentDetailActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repayment_detail);
        mBind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getPageTitle() {
        return R.string.repayment_detail;
    }

    private void initView() {
        List<String> data = Arrays.asList("1", "2", "3", "4");
        RepaymentDetailAdapter adapter = new RepaymentDetailAdapter(data);
        mRvList.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        RVItemDecoration itemDecoration = new RVItemDecoration(ContextCompat.getColor(this, R.color.gray_dc), 0.5f);
        mRvList.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CurrentPeriodRepaymentDialog dialog = new CurrentPeriodRepaymentDialog(RepaymentDetailActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @OnClick({R.id.ll_auto_withhold, R.id.ll_initiative_repayment, R.id.ll_alipay_repayment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_auto_withhold:
                new AutoWithholdDialog(this).show();
                break;
            case R.id.ll_initiative_repayment:
                break;
            case R.id.ll_alipay_repayment:
                break;
        }
    }
}
