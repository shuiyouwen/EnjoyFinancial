package com.example.shui.enjoyfinancial.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.adapter.ProductAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static com.example.shui.enjoyfinancial.utils.Utils.dip2px;

/**
 * 产品详情弹窗
 * Created by Shui on 2017/9/8.
 */

public class ProductDetailDialog extends BottomSheetDialog {
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_price)
    TextView mTvPrice;

    private Unbinder mBind;
    private final Activity mActivity;
    private View mView;

    public ProductDetailDialog(@NonNull Activity activity) {
        super(activity, R.style.TranslucentBottomSheetDialog);
        mActivity = activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = View.inflate(mActivity, R.layout.dialog_product_detail, null);
        setContentView(mView);
        mBind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //宽度充满屏幕
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //解决状态栏颜色不一致的问题
        int screenHeight = getScreenHeight(mActivity);
        int statusBarHeight = getStatusBarHeight(mActivity);
        int dialogHeight = screenHeight - statusBarHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);

        final List<String> datas = Arrays.asList("【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿", "【经典款】翡翠绿", "【经典款】翡翠绿"
                , "【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿", "【经典款】翡翠绿", "【经典款】翡翠绿", "【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿翠绿 "
                , "【经典款】翡翠绿翠绿 ", "【经典款】翡翠绿", "【经典款】翡翠绿", "【经典款】翡翠绿");
        ProductAdapter adapter = new ProductAdapter(datas);
        mRvProduct.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        mRvProduct.setLayoutManager(layoutManager);
        mRvProduct.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = dip2px(mActivity, 14);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ((ProductAdapter) adapter).resetAllView();
                view.setActivated(true);
            }
        });
        //recycleview加载完成后，展开dialog
        mRvProduct.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //显示全部布局
                View parent = (View) mView.getParent();
                BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
                behavior.setState(STATE_EXPANDED);
                behavior.setHideable(false);
            }
        });

        setPrice("5999", mTvPrice);
    }

    private static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    @Override
    public void onDetachedFromWindow() {
        mBind.unbind();
        super.onDetachedFromWindow();
    }

    @OnClick({R.id.iv_cancel, R.id.iv_add, R.id.iv_subtraction})
    public void onViewClicked(View view) {
        int num = Integer.parseInt(mTvNum.getText().toString());
        switch (view.getId()) {
            case R.id.iv_cancel:
                cancel();
                break;
            case R.id.iv_add:
                mTvNum.setText(String.valueOf(num + 1));
                break;
            case R.id.iv_subtraction:
                if (num > 1) {
                    mTvNum.setText(String.valueOf(num - 1));
                }
                break;
        }
    }

    /**
     * 设置价格
     */
    private void setPrice(String price, TextView textView) {
        price = String.format("￥%s", price);
        SpannableStringBuilder builder = new SpannableStringBuilder(price);
        AbsoluteSizeSpan sizeSpan1 = new AbsoluteSizeSpan(dip2px(mActivity, 12));
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.font_gray_6b));
        builder.setSpan(sizeSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(colorSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }
}
