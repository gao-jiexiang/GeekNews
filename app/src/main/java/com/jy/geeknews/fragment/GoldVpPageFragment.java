package com.jy.geeknews.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.jy.geeknews.R;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.base.Constants;
import com.jy.geeknews.presenter.EmptyPresenter;
import com.jy.geeknews.view.EmptyView;

import butterknife.BindView;

public class GoldVpPageFragment extends BaseFragment<EmptyPresenter> implements EmptyView {
    @BindView(R.id.txt_title)
    TextView mTitle;

    public static GoldVpPageFragment getInstance(String title){
        GoldVpPageFragment fragment = new GoldVpPageFragment();
        Bundle b = new Bundle();
        b.putString(Constants.DATA,title);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gold_vp_page;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EmptyPresenter();
    }

    @Override
    protected void initView() {
        String str = getArguments().getString(Constants.DATA);
        mTitle.setText(str);

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
