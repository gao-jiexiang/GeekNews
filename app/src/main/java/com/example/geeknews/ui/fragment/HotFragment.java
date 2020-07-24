package com.example.geeknews.ui.fragment;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvHotAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.HotBean;
import com.example.geeknews.presenter.HotPresenter;
import com.example.geeknews.view.HotView;

import java.util.ArrayList;

import butterknife.BindView;

public class HotFragment extends BaseFragment<HotPresenter> implements HotView {

    @BindView(R.id.rv)
    RecyclerView rv;
    private RlvHotAdapter adapter;
    private ArrayList<HotBean.RecentBean> hotList;

    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();

        return fragment;
    }

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected void initView(View inflate) {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        hotList = new ArrayList<>();
        adapter = new RlvHotAdapter(hotList, getContext());
        rv.setAdapter(adapter);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void setData(HotBean bean) {
        adapter.setData(bean);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }
}
