package com.jy.geeknews.fragment;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.jy.geeknews.R;
import com.jy.geeknews.adapter.GankVpFragmentAdapter;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.presenter.EmptyPresenter;
import com.jy.geeknews.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;

public class GankFragment extends BaseFragment<EmptyPresenter> implements EmptyView {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<String> mTabs;
    private ArrayList<BaseFragment> mFragments;
    private GankVpFragmentAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EmptyPresenter();
    }

    @Override
    protected void initView() {
        mTabs = new ArrayList<>();
        mTabs.add("福利");
        mTabs.add("休息视频");
        mTabs.add("Android");
        mTabs.add("前端");

        mFragments = new ArrayList<>();
        mFragments.add(new GankDetailFragment("福利"));
        mFragments.add(new GankDetail2Fragment("休息视频"));
        mFragments.add(new GankDetailFragment("Android"));
        mFragments.add(new GankDetailFragment("前端"));

        mAdapter = new GankVpFragmentAdapter(getChildFragmentManager(), mTabs, mFragments);
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
