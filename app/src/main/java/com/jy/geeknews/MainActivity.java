package com.jy.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.jy.geeknews.fragment.FlowlayoutFragment;
import com.jy.geeknews.fragment.GankFragment;
import com.jy.geeknews.fragment.GoldMainFragment;
import com.jy.geeknews.fragment.StickyHeaderFragment;
import com.jy.geeknews.fragment.WeChatMainFragment;
import com.jy.geeknews.fragment.ZhihuDailyFragment;
import com.jy.geeknews.util.ToastUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_title)
    TextView mTxtTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frame)
    FrameLayout mFrame;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.nav)
    NavigationView mNav;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;

    private FragmentManager mFm;
    private ZhihuDailyFragment mZhihuDailyFragment;
    private MenuItem mSearchItem;
    private WeChatMainFragment mWeChatMainFragment;
    private GoldMainFragment mGoldMainFragment;
    private StickyHeaderFragment mStickyHeaderFragment;
    private FlowlayoutFragment mFlowlayoutFragment;
    private GankFragment mGankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        //处理fragment
        initFragment();

    }

    private void initFragment() {
        mFm = getSupportFragmentManager();
        mZhihuDailyFragment = new ZhihuDailyFragment();
        mWeChatMainFragment = new WeChatMainFragment();
        mGoldMainFragment = new GoldMainFragment();
        mGankFragment = new GankFragment();

        mStickyHeaderFragment = new StickyHeaderFragment();
        mFlowlayoutFragment = new FlowlayoutFragment();
        mFm.beginTransaction().add(R.id.frame,mZhihuDailyFragment).commit();

    }

    private void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        //三个杠
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        //主界面和侧滑菜单一同向右移动
        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mLl.setX(mNav.getWidth()*slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.item1){//设置toolbar中间的title
                    mTxtTitle.setText("知乎日报");
                    mSearchItem.setVisible(false);//不显示搜索菜单按钮
                    //显示知乎日报的fragment
                    mFm.beginTransaction().replace(R.id.frame,mZhihuDailyFragment).commit();
                }else if(itemId == R.id.item2){
                    mTxtTitle.setText("微信精选");
                    mSearchItem.setVisible(true);//显示搜索菜单按钮
                    //显示微信精选的fragment，通过替换
                    mFm.beginTransaction().replace(R.id.frame,mWeChatMainFragment).commit();
                }else if(itemId == R.id.item3){
                    mTxtTitle.setText("稀土掘金");
                    mSearchItem.setVisible(true);//显示搜索菜单按钮
                    //显示微信精选的fragment，通过替换
                    mFm.beginTransaction().replace(R.id.frame,mGoldMainFragment).commit();
                }else if(itemId == R.id.item6){
                    mTxtTitle.setText("干货集中营");
                    mSearchItem.setVisible(false);//不显示搜索菜单按钮
                    mFm.beginTransaction().replace(R.id.frame,mGankFragment).commit();
                }else if(itemId == R.id.item4){
                    mTxtTitle.setText("粘性头部");
                    mSearchItem.setVisible(false);//不显示搜索菜单按钮
                    mFm.beginTransaction().replace(R.id.frame,mStickyHeaderFragment).commit();
                }else if(itemId == R.id.item5){
                    mTxtTitle.setText("流式布局");
                    mSearchItem.setVisible(false);//不显示搜索菜单按钮
                    mFm.beginTransaction().replace(R.id.frame,mFlowlayoutFragment).commit();
                }else if(itemId == R.id.item7){
                    startActivity(new Intent(MainActivity.this,AppbarActivity.class));
                }
                mDl.closeDrawer(Gravity.LEFT);//关闭侧滑
                return false;
            }
        });
        mSearchView.setCursorDrawable(R.drawable.custom_cursor);//设置搜索框中的光标的样式
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        //添加MaterialSearchView的搜索信息改变的监听
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {//点击 软键盘的 搜索 按钮回调此方法，query是搜索的信息
//                Toast.makeText(MainActivity.this, "提交信息："+query, Toast.LENGTH_SHORT).show();
                //直接到服务器请求搜索相关的信息，然后展示在列表中，，此处是入口
                mWeChatMainFragment.setSearchKey(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {//只有有改变立马回调此方法，newText是新信息
//                ToastUtil.showShort("新信息："+newText);
                //可以到服务器请求搜索相关的信息，，但是有点操之过急
                return false;
            }
        });
        //添加MaterialSearchView的展开关闭监听
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
//                ToastUtil.showShort("展开");
            }

            @Override
            public void onSearchViewClosed() {
//                ToastUtil.showShort("关闭");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        mSearchItem = menu.findItem(R.id.action_search);
        mSearchItem.setVisible(false);//设置为不显示，，知乎日报没有搜索功能
        mSearchView.setMenuItem(mSearchItem);//mSearchView和选项菜单进行结合绑定
        return true;
    }

    /**
     * 那回退键会调用这个方法
     */
    @Override
    public void onBackPressed() {//按下手机后退键会回调此方法
        if (mSearchView.isSearchOpen()){
            mSearchView.closeSearch();
        }else {
            super.onBackPressed();
        }
    }

}
