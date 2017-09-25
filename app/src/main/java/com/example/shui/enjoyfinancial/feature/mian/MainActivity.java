package com.example.shui.enjoyfinancial.feature.mian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.shui.enjoyfinancial.R;
import com.example.shui.enjoyfinancial.base.BaseActivity;
import com.example.shui.enjoyfinancial.feature.category.CategoryFragment;
import com.example.shui.enjoyfinancial.feature.home.HomeFragment;
import com.example.shui.enjoyfinancial.feature.mine.MineFragment;
import com.example.shui.enjoyfinancial.network.ResultSubject;
import com.example.shui.enjoyfinancial.network.RetrofitClient;
import com.example.shui.enjoyfinancial.network.bean.resp.AppVersionResp;
import com.example.shui.enjoyfinancial.network.helper.RxResultHelper;
import com.example.shui.enjoyfinancial.network.helper.RxSchedulersHelper;
import com.example.shui.enjoyfinancial.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主界面
 * Created by Shui on 2017/9/6.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private static final int HOME_FRAGMENT_POSITION = 0;
    private static final int CATEGORY_FRAGMENT_POSITION = 1;
    private static final int MINE_FRAGMENT_POSITION = 2;
    private Unbinder mBind;
    private List<Fragment> mFragments = new ArrayList<>();
    private int mLastPosition;//上个选中的position

    {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CategoryFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void initStateBar() {
        StatusBarUtil.stateBarSetting(this, true);
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }

    private void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            checkVersion();
            initBottomBar();
            loadMultiRootFragment();
        } else {
            showHindFragment(mLastPosition);
        }
    }

    private void checkVersion() {
        // TODO: 2017/9/6 检查更新版本
        RetrofitClient.getApi().appVersion("A")
                .compose(RxSchedulersHelper.ioMain())
                .compose(this.bindToLifecycle())
                .compose(RxResultHelper.handleResult())
                .subscribe(new ResultSubject<AppVersionResp>(this) {
                    @Override
                    public void onNext(AppVersionResp response) {

                    }
                });
    }

    /**
     * 加载多个fragment
     */
    private void loadMultiRootFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            fragmentTransaction.add(R.id.fl_container, mFragments.get(i));
            if (i != 0) {
                fragmentTransaction.hide(mFragments.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    /**
     * 显示一个fragment，隐藏其他所有fragment
     *
     * @param showPosition 显示fragment的position
     */
    private void showHindFragment(int showPosition) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            if (i == showPosition) {
                fragmentTransaction.show(mFragments.get(i));
            } else {
                fragmentTransaction.hide(mFragments.get(i));
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void initBottomBar() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setActiveColor(R.color.orange_cf);
        mBottomNavigationBar.setInActiveColor(R.color.font_gray_99);

        BottomNavigationItem item1 = new BottomNavigationItem(R.mipmap.home, R.string.home);
        BottomNavigationItem item2 = new BottomNavigationItem(R.mipmap.category, R.string.category);
        BottomNavigationItem item3 = new BottomNavigationItem(R.mipmap.mine, R.string.mine);
        mBottomNavigationBar.addItem(item1).addItem(item2).addItem(item3)
                .setFirstSelectedPosition(HOME_FRAGMENT_POSITION).initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mLastPosition = position;
                showHindFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }
}
