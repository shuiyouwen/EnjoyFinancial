package com.example.shui.enjoyfinancial.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.shui.enjoyfinancial.R;

/**
 * 自动扣款提示
 * Created by Shui on 2017/9/14.
 */

public class AutoWithholdDialog extends AlertDialog {


    public AutoWithholdDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_auto_withhold);
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        WindowManager.LayoutParams params = getWindow().getAttributes();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        params.width = dm.widthPixels * 300 / 375;
        getWindow().setAttributes(params);
    }
}
