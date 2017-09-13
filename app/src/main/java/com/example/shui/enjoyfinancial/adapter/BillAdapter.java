package com.example.shui.enjoyfinancial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.example.shui.enjoyfinancial.feature.mine.BillFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 账单
 * Created by Shui on 2017/9/13.
 */

public class BillAdapter extends FragmentPagerAdapter {
    private List<String> mTitles = Arrays.asList("当前账单", "历史账单");
    private List<BillFragment> mBaseFragments = new ArrayList<>();

    public BillAdapter(FragmentManager fm) {
        super(fm);
        mBaseFragments.add(BillFragment.newInstance());
        mBaseFragments.add(BillFragment.newInstance());
    }

    @Override
    public int getCount() {
        return mBaseFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mBaseFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
