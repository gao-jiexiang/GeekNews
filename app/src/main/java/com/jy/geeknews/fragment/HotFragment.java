package com.jy.geeknews.fragment;

import com.jy.geeknews.R;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.presenter.EmptyPresenter;
import com.jy.geeknews.view.EmptyView;

public class HotFragment extends BaseFragment<EmptyPresenter> implements EmptyView {
    @Override
    protected int getLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EmptyPresenter();
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
    public void showToast(String str) {

    }
}
