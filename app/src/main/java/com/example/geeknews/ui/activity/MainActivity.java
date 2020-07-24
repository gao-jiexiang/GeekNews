package com.example.geeknews.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.Constants;
import com.example.geeknews.bean.VerifyCodeBean;
import com.example.geeknews.presenter.MainPresenter;
import com.example.geeknews.ui.fragment.AboutFragment;
import com.example.geeknews.ui.fragment.CollectFragment;
import com.example.geeknews.ui.fragment.GankFragment;
import com.example.geeknews.ui.fragment.GoldFragment;
import com.example.geeknews.ui.fragment.SettingsFragment;
import com.example.geeknews.ui.fragment.V2exFragment;
import com.example.geeknews.ui.fragment.WechatFragment;
import com.example.geeknews.ui.fragment.ZhihuFragment;
import com.example.geeknews.util.LogUtil;
import com.example.geeknews.util.SpUtil;
import com.example.geeknews.view.MainView;
import com.example.geeknews.widget.UIModeUtil;
import com.google.android.material.navigation.NavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


//geeknews
public class MainActivity extends BaseActivity<MainPresenter>
        implements MainView {
    //如果是空的,说明没有切换日夜间模式,需要将保持的当前fragment的类型换成知乎fragment
    public String mFlag;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    private ArrayList<String> mTitles;
    private ArrayList<BaseFragment> mFragments;
    private static int TYPE_ZHIHU = 0;//代表知乎fragmnet的类型,还是fragment的索引
    private static int TYPE_WECHAT = 1;
    private static int TYPE_GANK = 2;
    private static int TYPE_GOLD = 3;
    private static int TYPE_V2EX = 4;
    private static int TYPE_COLLECT = 5;
    public static int TYPE_SETTINGS = 6;
    private static int TYPE_ABOUT = 7;
    private int lastFragmentPosition;
    private FragmentManager mManager;
    private MenuItem mItem;
    //android 9.0 不支持非加密网络请求,也就是说只支持https的请求,不支持http


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        //return new MainPresenter(this);
        //v层对象不适用构造传递,使用方法传递
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        //获取记录的fragment类型，显示它
        int type = (int) SpUtil.getParam(Constants.CURRENT_FRAG_TYPE, TYPE_ZHIHU);
          /*int mode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        UIModeUtil.setActModeUseMode(this,mode);*/
        UIModeUtil.setActModeFromSp(this);
        LogUtil.print(" mainactivity oncreate");
        mManager = getSupportFragmentManager();
        initTitles();
        mToolBar.setTitle(mTitles.get(type));
        setSupportActionBar(mToolBar);

        //旋转开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBar, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        initFragment();
        //一场来需要显示知乎的碎片
        addFisrtFragment(type);


    }

    private void addFisrtFragment(int type) {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fl, mFragments.get(type));
        transaction.commit();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(ZhihuFragment.newInstance());
        mFragments.add(WechatFragment.newInstance());
        mFragments.add(GankFragment.newInstance());
        mFragments.add(GoldFragment.newInstance());
        mFragments.add(V2exFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(SettingsFragment.newInstance());
        mFragments.add(AboutFragment.newInstance(mTitles.get(TYPE_ABOUT)));
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(getResources().getString(R.string.zhihu));
        mTitles.add(getResources().getString(R.string.wechat));
        mTitles.add(getResources().getString(R.string.gank));
        mTitles.add(getResources().getString(R.string.gold));
        mTitles.add(getResources().getString(R.string.v2ex));
        mTitles.add(getResources().getString(R.string.collect));
        mTitles.add(getResources().getString(R.string.settings));
        mTitles.add(getResources().getString(R.string.about));
    }


    @Override
    public void setData(VerifyCodeBean bean) {
    }

    @Override
    protected void initListener() {
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.info && itemId != R.id.option) {
                    item.setChecked(true);
                    mDl.closeDrawer(Gravity.LEFT);
                }

                switch (itemId) {
                    case R.id.zhihu:
                        switchFragment(TYPE_ZHIHU);
                        break;
                    case R.id.wechat:
                        switchFragment(TYPE_WECHAT);
                        break;
                    case R.id.gank:
                        switchFragment(TYPE_GANK);
                        break;
                    case R.id.gold:
                        switchFragment(TYPE_GOLD);
                        break;
                    case R.id.vtex:
                        switchFragment(TYPE_V2EX);
                        break;
                    case R.id.collect:
                        switchFragment(TYPE_COLLECT);
                        break;
                    case R.id.settings:
                        switchFragment(TYPE_SETTINGS);
                        break;
                    case R.id.about:
                        switchFragment(TYPE_ABOUT);
                        break;
                }
                //需要返回true
                return true;
            }
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交搜索文本
                showToast("提交:"+query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索文本发生改变
                showToast("改变:"+newText);
                return false;
            }
        });

        //搜索框显示隐藏监听
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                //showToast("show");
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                //showToast("close");
            }
        });
    }

    private void switchFragment(int type) {
        FragmentTransaction transaction = mManager.beginTransaction();
        BaseFragment addFragment = mFragments.get(type);
        //framgnet只能被添加一次,多次添加会崩
        if (!addFragment.isAdded()) {
            transaction.add(R.id.fl, addFragment);
        }


        //隐藏上一个碎片
        BaseFragment hideFragment = mFragments.get(lastFragmentPosition);
        transaction.hide(hideFragment);
        transaction.show(addFragment);
        //当前现实的碎片就是下次在点击侧滑菜单需要隐藏的fragment
        lastFragmentPosition = type;
        transaction.commit();

        //切换title
        mToolBar.setTitle(mTitles.get(type));

        //显示隐藏搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK){
            mItem.setVisible(true);
        }else {
            mItem.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        mItem = menu.findItem(R.id.action_search);
        mItem.setVisible(false);//隐藏
        mSearchView.setMenuItem(mItem);

        return true;
    }

    //点击返回键的时候回触发的回调方法
    @Override
    public void onBackPressed() {
        //搜索框是开着的
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        //通知m层将网络请求取消掉
        mPresenter.onDestory();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (TextUtils.isEmpty(mFlag)) {
            SpUtil.setParam(Constants.CURRENT_FRAG_TYPE,TYPE_ZHIHU);
        }
    }
}
