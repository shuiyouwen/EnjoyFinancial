package com.example.shui.enjoyfinancial.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shui.enjoyfinancial.App;

/**
 * 工具类
 * Created by Shui on 2017/9/6.
 */

public class Utils {
    public static void strikethroughTextView(TextView textView) {
        TextPaint paint = textView.getPaint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private static Toast sToast;

    /**
     * 弹出toast
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context, String message) {
        if (sToast == null) {
            sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
        }
        sToast.show();
    }

    public static void showToast(Context context, @StringRes int messageRes) {
        showToast(context, context.getString(messageRes));
    }

    /**
     * 设置价格
     *
     * @param price
     * @param textView
     * @param spSize
     * @param colorRes
     */
    public static void setPrice(String price, TextView textView, int spSize, @ColorRes int colorRes) {
        price = String.format("￥%s", price);
        SpannableStringBuilder builder = new SpannableStringBuilder(price);
        if (colorRes != 0) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(App.sContext, colorRes));
            builder.setSpan(colorSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (spSize != 0) {
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(dip2px(App.sContext, spSize));
            builder.setSpan(sizeSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(builder);
    }

    /**
     * 使用glide加载图片
     *
     * @param imgUrl      图片地址
     * @param imageView   加载图片控件
     * @param placeholder 未加载图片占位图
     * @param error       加载错误占位图
     */// TODO: 2017/9/25 默认图片，和错误图片
    public static void loadImage(String imgUrl, ImageView imageView, int placeholder, int error) {
        Glide.with(App.sContext)
                .load(imgUrl)
                .placeholder(placeholder)
                .error(error)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 格式化金额，不保留小数
     */
    public static String formatAmt(String amt) {
        double d = Double.parseDouble(amt);
        String amtStr = (int) d + "";
        return amtStr;
    }
}
