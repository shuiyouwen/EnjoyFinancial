package com.example.shui.enjoyfinancial.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.network.bean.resp.BannerResp;

import java.util.List;

/**
 * banner图adapter
 * Created by Shui on 2017/9/6.
 */

public class BannerAdapter extends PagerAdapter {

    private final List<BannerResp> mBannerResps;
    private final Activity mActivity;

    public BannerAdapter(List<BannerResp> bannerResps, Activity activity) {
        mBannerResps = bannerResps;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mBannerResps.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mActivity);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.index_banner);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
