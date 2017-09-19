package com.example.shui.enjoyfinancial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shui.enjoyfinancial.feature.mine.CouponListFragment;

import java.util.Arrays;
import java.util.List;

/**
 * 代金券
 * Created by Shui on 2017/9/14.
 */

public class CouponAdapter extends FragmentPagerAdapter {
    private List<String> mTitles = Arrays.asList("可使用", "历史记录");
    private List<CouponListFragment> mCouponListFragments = Arrays.asList(CouponListFragment.newInstance()
            , CouponListFragment.newInstance());

    public CouponAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mCouponListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
