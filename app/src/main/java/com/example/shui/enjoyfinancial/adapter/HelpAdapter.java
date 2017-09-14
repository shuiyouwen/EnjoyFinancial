package com.example.shui.enjoyfinancial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shui.enjoyfinancial.feature.mine.HelpListFragment;

import java.util.Arrays;
import java.util.List;

/**
 * 帮助
 * Created by Shui on 2017/9/13.
 */

public class HelpAdapter extends FragmentPagerAdapter {
    private List<String> mTitles = Arrays.asList("信用钱包", "分期购物", "账户还款", "账户安全");
    private List<HelpListFragment> mFragments = Arrays.asList(HelpListFragment.newInstance()
            , HelpListFragment.newInstance(), HelpListFragment.newInstance(), HelpListFragment.newInstance());

    public HelpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
