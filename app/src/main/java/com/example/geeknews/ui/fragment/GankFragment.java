package com.example.geeknews.ui.fragment;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.example.geeknews.R;
import com.example.geeknews.adapter.VpZhihuAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.presenter.GankPresenter;
import com.example.geeknews.view.GankItemView;
import com.example.geeknews.view.GankView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;


public class GankFragment extends BaseFragment<GankPresenter> implements GankView {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<String> mTitles;
    private ArrayList<BaseFragment> mFragments;

    public static BaseFragment newInstance() {
        GankFragment fragment = new GankFragment();

        return fragment;
    }

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView(View inflate) {
        initTitles();
        initFragment();

        VpZhihuAdapter adapter = new VpZhihuAdapter(getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(adapter);
        mTablayout.setupWithViewPager(mVp);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mFragments.add(GankItemFragment.newInstance(mTitles.get(i)));
        }
        mFragments.add(GirlFragment.newInstance());
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add("Android");
        mTitles.add("iOS");
        mTitles.add("前端");
        mTitles.add("福利");
    }
}
