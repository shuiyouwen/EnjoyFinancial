package com.example.shui.enjoyfinancial.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shui.enjoyfinancial.network.bean.resp.AdResp;
import com.example.shui.enjoyfinancial.utils.Utils;

import java.util.List;

/**
 * banner图adapter
 * Created by Shui on 2017/9/6.
 */

public class BannerAdapter extends PagerAdapter {

    private final List<AdResp> mBannerResps;
    private final Activity mActivity;

    public BannerAdapter(List<AdResp> bannerResps, Activity activity) {
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
        AdResp adResp = mBannerResps.get(position);
        ImageView imageView = new ImageView(mActivity);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Utils.loadImage(adResp.getImg_url(), imageView, 0, 0);
        if (adResp.isSkip()) {
            // TODO: 2017/9/22 跳转广告
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
