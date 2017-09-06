package com.example.shui.enjoyfinancial.feature.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.widget.magicindicator.MagicIndicator;
import com.example.shui.enjoyfinancial.widget.viewpager.CycleGalleryViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.shui.enjoyfinancial.utils.Utils.dip2px;
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

        setPrice1("5899", mTvPrice);
        mTvOriginalPrice2.setVisibility(View.GONE);
        setPrice("1999", mTvPrice3);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_look_more)
    public void onViewClicked() {
    }

    /**
     * 设置第一个产品的价格
     */
    private void setPrice1(String price, TextView textView) {
        price = String.format("￥%s", price);
        SpannableStringBuilder builder = new SpannableStringBuilder(price);
        AbsoluteSizeSpan sizeSpan1 = new AbsoluteSizeSpan(dip2px(mActivity, 12));
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.font_gray_6b));
        builder.setSpan(sizeSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(colorSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    /**
     * 设置第一个产品之外的价格
     */
    private void setPrice(String price, TextView textView) {
        price = String.format("￥%s", price);
        SpannableStringBuilder builder = new SpannableStringBuilder(price);
        AbsoluteSizeSpan sizeSpan1 = new AbsoluteSizeSpan(dip2px(mActivity, 10));
        builder.setSpan(sizeSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }
}
