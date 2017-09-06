package com.example.shui.enjoyfinancial.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


/**
 * fragment基类，对rxjava可以和其子类的生命周期绑定
 * Created by Shui on 2017/7/5.
 */

public class BaseFragment extends Fragment implements LifecycleProvider<FragmentEvent>, IBaseView {
//    @Nullable
//    @BindView(R.id.swipe_refresh_layout)
//    protected SwipeRefreshLayout mSwipeRefreshLayout;

    private KProgressHUD mKProgressHUD;
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected Activity mActivity;
//    private MessageDialog mDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
        mActivity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        initUi();
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }


    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }


    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }


    /**
     * 初始化通用ui
     */
    protected void initUi() {
//        if (mSwipeRefreshLayout != null) {
//            mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.orange_f5));
//        }
    }

    @Override
    public void openLoading() {
        if (getActivity() == null) return;
        //若是下拉刷新，则无需弹出阻塞进度提示
//        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) return;

        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(getActivity())
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
        if (getActivity() == null) return;

        if (mKProgressHUD != null && mKProgressHUD.isShowing()) {
            mKProgressHUD.dismiss();
        }

//        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
    }

    @Override
    public void showError(String errorMsg) {
//        Utils.showToast(mActivity, errorMsg);
    }

    @Override
    public void launchLogin(boolean isShowMsg, String msg) {
        if (getActivity() == null) return;
//        if (isShowMsg) {
//            mDialog = new MessageDialog(mActivity, msg, getString(R.string.cancel), new View.OnClickListener() {
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
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQ_LOGIN && resultCode == LoginActivity.RESULT_LOGIN_SUCCESS) {
//            loginBack();
//        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * runnable执行在登录完成之后
     *
     * @param runnable
     */
    protected void runOnLogging(Runnable runnable) {
//        if (AccountManager.getInstance().isLogging()) {
//            runnable.run();
//        } else {
//            startLoginActivity();
//        }
    }

    /**
     * 设置网络错误界面
     *
     * @param runnable
     * @param adapter
     */
    protected void setNetErrorView(final Runnable runnable, BaseQuickAdapter adapter) {
//        View netErrorView = LayoutInflater.from(mActivity).inflate(R.layout.layout_net_error, null);
//        netErrorView.findViewById(R.id.btn_reload).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isNetworkConnected()) {
//                    Utils.showToast(App.sContext, R.string.dont_have_net);
//                } else {
//                    runnable.run();
//                }
//            }
//        });
//        adapter.setEmptyView(netErrorView);
    }
}
