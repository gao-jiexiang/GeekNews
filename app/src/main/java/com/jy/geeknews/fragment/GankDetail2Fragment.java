package com.jy.geeknews.fragment;

import com.jy.geeknews.R;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.bean.GankBean;
import com.jy.geeknews.presenter.GankDetailPresenter;
import com.jy.geeknews.view.GankDetailView;

public class GankDetail2Fragment extends BaseFragment<GankDetailPresenter> implements GankDetailView {
    private String tech;

    public GankDetail2Fragment(String tech) {
        this.tech = tech;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_detail;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new GankDetailPresenter();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setGankData(GankBean gankBean) {

    }

    @Override
    public void showToast(String str) {

    }
}
