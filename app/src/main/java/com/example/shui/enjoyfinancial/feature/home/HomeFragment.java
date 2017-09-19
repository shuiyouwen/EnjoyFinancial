package com.example.shui.enjoyfinancial.feature.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.BannerAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.network.RxSchedulersHelper;
import com.example.shui.enjoyfinancial.network.bean.resp.BannerResp;
import com.example.shui.enjoyfinancial.utils.Utils;
import com.example.shui.enjoyfinancial.widget.magicindicator.MagicIndicator;
import com.example.shui.enjoyfinancial.widget.magicindicator.SolidCircleNavigator;
import com.example.shui.enjoyfinancial.widget.magicindicator.ViewPagerHelper;
import com.example.shui.enjoyfinancial.widget.viewpager.CycleGalleryViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.example.shui.enjoyfinancial.utils.Utils.dip2px;
import static com.example.shui.enjoyfinancial.utils.Utils.setPrice;
import static com.example.shui.enjoyfinancial.utils.Utils.strikethroughTextView;

/**
 * 首页
 * Created by Shui on 2017/9/6.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.vp_banner)
    CycleGalleryViewPager mVpBanner;
    @BindView(R.id.iv_banner_bottom)
    ImageView mIvBannerBottom;
    @BindView(R.id.tv_explode)
    TextView mTvExplode;
    @BindView(R.id.iv_product1)
    ImageView mIvProduct1;
    @BindView(R.id.tv_product1)
    TextView mTvProduct1;
    @BindView(R.id.tv_original_price1)
    TextView mTvOriginalPrice1;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_preferential_price1)
    TextView mTvPreferentialPrice1;
    @BindView(R.id.tv_cxf1)
    TextView mTvCxf1;
    @BindView(R.id.iv_product2)
    ImageView mIvProduct2;
    @BindView(R.id.tv_product_2)
    TextView mTvProduct2;
    @BindView(R.id.tv_original_price2)
    TextView mTvOriginalPrice2;
    @BindView(R.id.tv_price_2)
    TextView mTvPrice2;
    @BindView(R.id.tv_month_price2)
    TextView mTvMonthPrice2;
    @BindView(R.id.iv_product3)
    ImageView mIvProduct3;
    @BindView(R.id.tv_product_3)
    TextView mTvProduct3;
    @BindView(R.id.tv_original_price3)
    TextView mTvOriginalPrice3;
    @BindView(R.id.tv_price_3)
    TextView mTvPrice3;
    @BindView(R.id.tv_month_price3)
    TextView mTvMonthPrice3;
    @BindView(R.id.iv_product4)
    ImageView mIvProduct4;
    @BindView(R.id.tv_product_4)
    TextView mTvProduct4;
    @BindView(R.id.tv_original_price4)
    TextView mTvOriginalPrice4;
    @BindView(R.id.tv_price_4)
    TextView mTvPrice4;
    @BindView(R.id.tv_month_price4)
    TextView mTvMonthPrice4;
    Unbinder unbinder;
    @BindView(R.id.magic_indicator_banner)
    MagicIndicator mMagicIndicatorBanner;

    private Disposable mBannerSubscribe;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        strikethroughTextView(mTvOriginalPrice1);
        strikethroughTextView(mTvOriginalPrice2);
        strikethroughTextView(mTvOriginalPrice3);
        strikethroughTextView(mTvOriginalPrice4);

        setPrice("5899", mTvPrice, 12, R.color.font_gray_6b);
        mTvOriginalPrice2.setVisibility(View.GONE);
        setPrice("1999", mTvPrice2, 10, 0);
        setPrice("1999", mTvPrice3, 10, 0);
        setPrice("1999", mTvPrice4, 10, 0);

        List<BannerResp> bannerResps = new ArrayList<>();
        bannerResps.add(new BannerResp());
        bannerResps.add(new BannerResp());
        bannerResps.add(new BannerResp());
        BannerAdapter adapter = new BannerAdapter(bannerResps, mActivity);
        mVpBanner.setAdapter(adapter);
        mVpBanner.setNarrowFactor(1f);
        //点击取消轮播，抬起手指继续轮播
        mVpBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cancelCarousel();
                        break;
                    case MotionEvent.ACTION_UP:
                        startCarousel();
                        break;
                }
                return false;
            }
        });
        initProductIndicator(bannerResps);
        startCarousel();
    }

    private void initProductIndicator(List<BannerResp> response) {
        SolidCircleNavigator circleNavigator = new SolidCircleNavigator(mActivity);
        circleNavigator.setCircleSpacing(Utils.dip2px(mActivity, 5));
        circleNavigator.setCircleCount(response.size());
        circleNavigator.setSelectColor(ContextCompat.getColor(mActivity, R.color.gray_ee));
        circleNavigator.setNormalColor(ContextCompat.getColor(mActivity, R.color.gray_77));
        mMagicIndicatorBanner.setNavigator(circleNavigator);
        ViewPagerHelper.bindCycleViewPager(mMagicIndicatorBanner, mVpBanner, response.size());
    }

    /**
     * 取消banner轮播
     */
    private void cancelCarousel() {
        if (mBannerSubscribe != null && !mBannerSubscribe.isDisposed()) {
            mBannerSubscribe.dispose();
        }
    }

    /**
     * 启动banner轮播
     */
    private void startCarousel() {
        cancelCarousel();
        //一张的时候不需要轮播
        if (mVpBanner.getAdapter() != null) {
            int count = mVpBanner.getAdapter().getCount();
            if (count <= 1) {
                return;
            }
        }
        mBannerSubscribe = Observable.interval(3, 3, TimeUnit.SECONDS)
                .compose(RxSchedulersHelper.<Long>ioMain())
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int currentItem = mVpBanner.getCurrentItem();
                        mVpBanner.setCurrentItem(++currentItem);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_look_more)
    public void onViewClicked() {
    }

}
