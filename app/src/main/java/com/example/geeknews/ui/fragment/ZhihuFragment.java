package com.example.geeknews.ui.fragment;


import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.example.geeknews.R;
import com.example.geeknews.adapter.VpZhihuAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class ZhihuFragment extends BaseFragment<ZhihuPresenter> implements ZhihuView {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<String> mTitles;
    private ArrayList<BaseFragment> mFragments;

    public static ZhihuFragment newInstance() {
        ZhihuFragment fragment = new ZhihuFragment();

        return fragment;
    }

    @Override
    protected ZhihuPresenter initPresenter() {
        return new ZhihuPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView(View inflate) {
        initFragment();
        initTitle();

        //fragment嵌套fragment,
        VpZhihuAdapter adapter = new VpZhihuAdapter(getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(adapter);

        //关联viewpager和tabLayout
        mTablayout.setupWithViewPager(mVp);
    }

    private void initTitle() {
        mTitles = new ArrayList<>();
        mTitles.add(getResources().getString(R.string.daily_news));
        mTitles.add(getResources().getString(R.string.section));
        mTitles.add(getResources().getString(R.string.hot));
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(DailyNewsFragment.newInstance());
        mFragments.add(SectionFragment.newInstance());
        mFragments.add(HotFragment.newInstance());
    }
}
