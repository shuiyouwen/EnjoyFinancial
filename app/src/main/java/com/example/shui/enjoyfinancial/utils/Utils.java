package com.example.shui.enjoyfinancial.utils;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.widget.TextView;

/**
 * 工具类
 * Created by Shui on 2017/9/6.
 */

public class Utils {
    public static void strikethroughTextView(TextView textView) {
        TextPaint paint = textView.getPaint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
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
}
