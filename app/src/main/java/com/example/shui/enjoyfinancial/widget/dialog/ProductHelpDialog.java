package com.example.shui.enjoyfinancial.widget.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.feature.mine.HelperActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * 商品帮助弹窗
 * Created by Shui on 2017/9/19.
 */

public class ProductHelpDialog extends BottomSheetDialog {

    private final Activity mActivity;
    private Unbinder mBind;

    public ProductHelpDialog(@NonNull Activity activity) {
        super(activity, R.style.TranslucentBottomSheetDialog);
        mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_product_help);
        mBind = ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(new BitmapDrawable());
        WindowManager.LayoutParams params = getWindow().getAttributes();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        params.width = dm.widthPixels;
        getWindow().setAttributes(params);
        Observable.create(e -> {
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
            e.onComplete();
        }).subscribe(integer -> Log.d("ProductHelpDialog", "integer:" + integer));
    }

    @Override
    public void onDetachedFromWindow() {
        mBind.unbind();
        super.onDetachedFromWindow();
    }

    @OnClick({R.id.tv_phone, R.id.tv_help, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                callPhone();
                break;
            case R.id.tv_help:
                mActivity.startActivity(new Intent(mActivity, HelperActivity.class));
                break;
            case R.id.btn_cancel:
                break;
        }
        cancel();
    }

    private void callPhone() {
        Uri uri = Uri.parse("tel:10086");
        Intent dialntent = new Intent(Intent.ACTION_DIAL, uri);
        mActivity.startActivity(dialntent);
    }
}
