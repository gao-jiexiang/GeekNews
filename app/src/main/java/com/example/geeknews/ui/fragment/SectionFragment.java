package com.example.geeknews.ui.fragment;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvSectionAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.SectionBean;
import com.example.geeknews.presenter.SectionPresenter;
import com.example.geeknews.view.SectionView;

import java.util.ArrayList;

import butterknife.BindView;

public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionView {

    @BindView(R.id.recy)
    RecyclerView recy;
    private ArrayList<SectionBean.DataBean> sectionList;
    private RlvSectionAdapter adapter;

    public static SectionFragment newInstance() {
        SectionFragment fragment = new SectionFragment();
        return fragment;
    }

    @Override
    protected SectionPresenter initPresenter() {
        return new SectionPresenter();
    }





    @Override
    protected void initView(View inflate) {
        recy.setLayoutManager(new GridLayoutManager(getContext(),2));
        sectionList = new ArrayList<>();
        adapter = new RlvSectionAdapter(getContext(),sectionList);
        recy.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    protected void initData() {
        mPresenter.getNews();
    }

    @Override
    public void setData(SectionBean bean) {
        adapter.setData(bean);
    }


}
