package com.example.shui.enjoyfinancial.feature.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseFragment;

/**
 * 分类界面
 * Created by Shui on 2017/9/6.
 */

public class CategoryFragment extends BaseFragment {
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
        return inflate;
    }
}
