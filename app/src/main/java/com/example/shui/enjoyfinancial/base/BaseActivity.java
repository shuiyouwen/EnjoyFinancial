package com.example.shui.enjoyfinancial.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.feature.login.LoginActivity;
import com.example.shui.enjoyfinancial.utils.StatusBarUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

import static com.example.shui.enjoyfinancial.constants.C.REQ_LOGIN;


/**
 * Activity基类，rxjava和其子类生命周期绑定
 * Created by Shui on 2017/7/5.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent>, IBaseView {
    @Nullable
    @BindView(R.id.fl_title_container)
    FrameLayout mFlTitleContainer;
    @Nullable
    @BindView(R.id.tv_title)
    protected TextView mTvTitle;
    @Nullable
//    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    private KProgressHUD mKProgressHUD;
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
//    private MessageDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        initSystemFont();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initUI();
    }

    /**
     * 初始化通用UI
     */
    protected void initUI() {
        initStateBar();
        int pageTitleRes = getPageTitle();
        if (mTvTitle != null && pageTitleRes != 0) {
            mTvTitle.setText(pageTitleRes);
        }
//        if (mSwipeRefreshLayout != null) {
//            mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.orange_f5));
//        }
    }

    protected @StringRes
    int getPageTitle() {
        return 0;
    }

    /**
     * 默认的沉浸式状态栏
     */
    protected void initStateBar() {
        if (mFlTitleContainer != null) {
            StatusBarUtil.stateBarSetting(this, mFlTitleContainer, false);
        }
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    /**
     * 初始化系统的字体大小，不受设置里的字体大小的影响
     */
    private void initSystemFont() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @Override
    public void openLoading() {
        //若是下拉刷新，则无需弹出阻塞进度提示
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) return;

        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f)
                    .setCancellable(true);
        }
        if (!mKProgressHUD.isShowing()) {
            mKProgressHUD.show();
        }
    }

    @Override
    public void closeLoading() {
        if (mKProgressHUD != null && mKProgressHUD.isShowing()) {
            mKProgressHUD.dismiss();
        }

        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchLogin(boolean isShowMsg, String msg) {
//        if (isShowMsg) {
//            mDialog = new MessageDialog(this, msg, getString(R.string.cancel), new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mDialog.cancel();
//                    RxBus.get().post(TagConstants.BACK_HOME, "返回首页");
//                    AccountManager.getInstance().exit();
//                }
//            }, getString(R.string.go_login), new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mDialog.cancel();
//                    startLoginActivity();
//                }
//            }, false);
//            mDialog.show();
//        } else {
//            startLoginActivity();
//        }
    }

    /**
     * 启动登录Activity
     */
    private void startLoginActivity() {
//        Intent intent = new Intent(this, LoginActivity.class);
//        intent.putExtra(REQ_LOGIN_KEY, REQ_LOGIN);
//        startActivityForResult(intent, REQ_LOGIN);
    }

    @Override
    public void netError(Throwable e) {

    }

    @Override
    public void loginBack() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_LOGIN && resultCode == LoginActivity.RESULT_LOGIN_SUCCESS) {
            loginBack();
        }
    }

    /**
     * 点击返回按钮
     */
    @Optional
    @OnClick(R.id.iv_back)
    protected void onBackClicked() {
        onBackPressed();
    }
}
