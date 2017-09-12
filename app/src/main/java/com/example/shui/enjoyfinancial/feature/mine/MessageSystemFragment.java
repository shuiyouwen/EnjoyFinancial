package com.example.shui.enjoyfinancial.feature.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.MessageSystemAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.widget.RVItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 系统消息fragment
 * Created by Shui on 2017/9/12.
 */

public class MessageSystemFragment extends BaseFragment {
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;
    Unbinder unbinder;

    public static MessageSystemFragment newInstance() {

        Bundle args = new Bundle();

        MessageSystemFragment fragment = new MessageSystemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_message_system, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
//        List<String> data = Arrays.asList("1", "2", "3", "4");
        List<String> data = new ArrayList<>();
        MessageSystemAdapter adapter = new MessageSystemAdapter(data);
        mRvMessage.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRvMessage.setLayoutManager(layoutManager);
        RVItemDecoration itemDecoration = new RVItemDecoration(Color.TRANSPARENT, 10);
        mRvMessage.addItemDecoration(itemDecoration);
        View inflate = View.inflate(mActivity, R.layout.empty_message, null);
        adapter.setEmptyView(inflate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
