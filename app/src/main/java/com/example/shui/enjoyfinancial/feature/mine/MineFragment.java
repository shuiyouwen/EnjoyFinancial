package com.example.shui.enjoyfinancial.feature.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseFragment;

/**
 * 我的界面
 * Created by Shui on 2017/9/6.
 */

public class MineFragment extends BaseFragment {
    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        return inflate;
    }
}
