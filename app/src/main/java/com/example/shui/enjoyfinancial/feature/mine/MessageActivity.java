package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.View;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 消息界面
 * Created by Shui on 2017/9/12.
 */

public class MessageActivity extends BaseActivity {

    private Unbinder mBind;
    private SparseArray<BaseFragment> mFragmentSparseArray = new SparseArray<>();
    private MessageRecommendFragment mMessageRecommendFragment = MessageRecommendFragment.newInstance();
    private MessageSystemFragment mMessageSystemFragment = MessageSystemFragment.newInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mBind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getPageTitle() {
        return R.string.message_center;
    }

    private void initView() {
        mFragmentSparseArray.put(R.id.tv_recommend, mMessageRecommendFragment);
        mFragmentSparseArray.put(R.id.tv_system, mMessageSystemFragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_container, mMessageRecommendFragment)
                .add(R.id.fl_container, mMessageSystemFragment)
                .hide(mMessageSystemFragment)
                .show(mMessageRecommendFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.tv_recommend, R.id.tv_system})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mMessageSystemFragment)
                .hide(mMessageRecommendFragment)
                .show(mFragmentSparseArray.get(view.getId()))
                .commit();
    }

}
