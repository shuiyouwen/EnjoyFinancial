package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.MessageRecommendAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 推荐消息fragment
 * Created by Shui on 2017/9/12.
 */

public class MessageRecommendFragment extends BaseFragment {
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;
    Unbinder unbinder;

    public static MessageRecommendFragment newInstance() {

        Bundle args = new Bundle();

        MessageRecommendFragment fragment = new MessageRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_message_recommend, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        List<String> data = Arrays.asList("1", "2", "3", "4");
        MessageRecommendAdapter adapter = new MessageRecommendAdapter(data);
        mRvMessage.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRvMessage.setLayoutManager(layoutManager);
        RVItemDecoration itemDecoration = new RVItemDecoration(ContextCompat.getColor(mActivity, R.color.gray_dc), 0.5f);
        mRvMessage.addItemDecoration(itemDecoration);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
