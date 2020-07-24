package com.jy.geeknews.fragment;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.geeknews.R;
import com.jy.geeknews.adapter.VpFragmentAdapter;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.presenter.ZhihuDailyPresenter;
import com.jy.geeknews.view.ZhihuDailyView;

import java.util.ArrayList;

import butterknife.BindView;

public class ZhihuDailyFragment extends BaseFragment<ZhihuDailyPresenter> implements ZhihuDailyView {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Integer> mTabTitle;
    private ArrayList<BaseFragment> mFragments;
    private VpFragmentAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhihu_dailay;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ZhihuDailyPresenter();
    }

    @Override
    protected void initView() {
        //处理tablayout,收集tab页的名字
        mTabTitle = new ArrayList<>();
        mTabTitle.add(R.string.daily);
        mTabTitle.add(R.string.special);
        mTabTitle.add(R.string.hot);

        //为ViewPager创建page页 三个fragment
        mFragments = new ArrayList<>();
        mFragments.add(new DailyNewsFragment());
        mFragments.add(new SpecialFragment());
        mFragments.add(new HotFragment());
        //
        mAdapter = new VpFragmentAdapter(getActivity().getSupportFragmentManager(), mTabTitle, mFragments);
        mVp.setAdapter(mAdapter);

        mTab.setupWithViewPager(mVp);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showToast(String str) {

    }
}
