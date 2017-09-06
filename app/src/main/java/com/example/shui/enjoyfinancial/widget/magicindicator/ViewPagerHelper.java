package com.example.shui.enjoyfinancial.widget.magicindicator;

import android.support.v4.view.ViewPager;

import com.example.shui.enjoyfinancial.widget.viewpager.CycleGalleryViewPager;


/**
 * 简化和ViewPager绑定
 * Created by hackware on 2016/8/17.
 */

public class ViewPagerHelper {
    public static void bind(final MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    public static void bindCycleViewPager(final MagicIndicator magicIndicator, CycleGalleryViewPager viewPager, final int size) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                position = getRealPosition(position, size);
//                Log.d("ViewPagerHelper", "onPageScrolled:" + position);
//                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
//                magicIndicator.onPageSelected(position);
                magicIndicator.onPageScrolled(position, 0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    private static int getRealPosition(int position, int totalCount) {
        if (position < 0) {
            return (position + totalCount) % totalCount;
        } else {
            return position % totalCount;
        }
    }

    /**
     * 无线循环的viewpager
     *
     * @param magicIndicator
     * @param viewPager
     */
    public static void bindForLoop(final MagicIndicator magicIndicator, ViewPager viewPager, final int size) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                position = position % size;
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                position = position % size;
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }
}
