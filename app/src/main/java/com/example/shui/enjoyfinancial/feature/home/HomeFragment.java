package com.example.shui.enjoyfinancial.feature.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.BannerAdapter;
import com.example.shui.enjoyfinancial.base.BaseFragment;
import com.example.shui.enjoyfinancial.network.ResultSubject;
import com.example.shui.enjoyfinancial.network.RetrofitClient;
import com.example.shui.enjoyfinancial.network.bean.resp.AdResp;
import com.example.shui.enjoyfinancial.network.bean.resp.ProductResp;
import com.example.shui.enjoyfinancial.network.helper.RxResultHelper;
import com.example.shui.enjoyfinancial.network.helper.RxSchedulersHelper;
import com.example.shui.enjoyfinancial.utils.Utils;
import com.example.shui.enjoyfinancial.widget.magicindicator.MagicIndicator;
import com.example.shui.enjoyfinancial.widget.magicindicator.SolidCircleNavigator;
import com.example.shui.enjoyfinancial.widget.magicindicator.ViewPagerHelper;
import com.example.shui.enjoyfinancial.widget.viewpager.CycleGalleryViewPager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.example.shui.enjoyfinancial.utils.Utils.formatAmt;
import static com.example.shui.enjoyfinancial.utils.Utils.loadImage;
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
    @BindView(R.id.tv_price1)
    TextView mTvPrice1;
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
    @BindView(R.id.tv_month_price1)
    TextView mTvMonthPrice1;
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
        initBanner();
        initCarAd();
        initProduct();
        assert mSwipeRefreshLayout != null;
        mSwipeRefreshLayout.setOnRefreshListener(this::initView);
    }

    /**
     * 初始化车险广告
     */
    private void initCarAd() {
        RetrofitClient.getApi().adList("CAR-INS")
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .compose(RxResultHelper.handleResult())
                .subscribe(new ResultSubject<List<AdResp>>(this) {
                    @Override
                    public void onNext(List<AdResp> response) {
                        Utils.loadImage(response.get(0).getImg_url(), mIvBannerBottom, 0, 0);
                    }
                });
    }

    private void initProduct() {
        RetrofitClient.getApi().recommend()
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .compose(RxResultHelper.handleResult())
                .subscribe(new ResultSubject<List<ProductResp>>(this) {
                    @Override
                    public void onNext(List<ProductResp> response) {
                        renderProduct(response);
                    }
                });
    }

    /**
     * 渲染推荐产品信息
     *
     * @param response
     */
    private void renderProduct(List<ProductResp> response) {
        for (int i = 0; i < response.size(); i++) {
            ProductResp productResp = response.get(i);
            try {
                int position = i + 1;
                ImageView ivProduct = (ImageView) getClass().getField("mIvProduct" + position).get(this);
                loadImage(productResp.getImg_url(), ivProduct, 0, 0);

                TextView tvProduct = (TextView) getClass().getField("mTvProduct" + position).get(this);
                tvProduct.setText(productResp.getTitle());

                int fontSize = i == 0 ? 12 : 10;
                int colorRes = i == 0 ? R.color.font_gray_6b : 0;
                TextView tvPrice = (TextView) getClass().getField("mTvPrice" + position).get(this);
                setPrice(formatAmt(productResp.getPreferent_min()), tvPrice, fontSize, colorRes);

                TextView tvOriginalPrice = (TextView) getClass().getField("mTvOriginalPrice" + position).get(this);
                strikethroughTextView(tvOriginalPrice);
                if (!TextUtils.isEmpty(productResp.getPrice_min())) {
                    tvOriginalPrice.setText(String.format("￥%s", formatAmt(productResp.getPrice_min())));
                }

                TextView tvMonthPrice = (TextView) getClass().getField("mTvMonthPrice" + position).get(this);
                String monthPrice = productResp.isSupportStages() ?
                        String.format("￥%s/月 起", formatAmt(productResp.getMonthly_min())) : "暂不支持";
                tvMonthPrice.setText(monthPrice);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void initBanner() {
        RetrofitClient.getApi().adList("BANNER")
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .compose(RxResultHelper.handleResult())
                .subscribe(new ResultSubject<List<AdResp>>(this) {
                    @Override
                    public void onNext(List<AdResp> response) {
                        BannerAdapter adapter = new BannerAdapter(response, mActivity);
                        mVpBanner.setAdapter(adapter);
                        mVpBanner.setNarrowFactor(1f);
                        //点击取消轮播，抬起手指继续轮播
                        mVpBanner.setOnTouchListener((v, event) -> {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    cancelCarousel();
                                    break;
                                case MotionEvent.ACTION_UP:
                                    startCarousel();
                                    break;
                            }
                            return false;
                        });
                        initProductIndicator(response);
                        startCarousel();
                    }
                });
    }

    private void initProductIndicator(List<AdResp> response) {
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
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .subscribe(aLong -> {
                    int currentItem = mVpBanner.getCurrentItem();
                    mVpBanner.setCurrentItem(++currentItem);
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
