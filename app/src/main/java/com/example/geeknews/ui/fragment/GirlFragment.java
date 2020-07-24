package com.example.geeknews.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvGirlAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.FuLiBean;
import com.example.geeknews.presenter.GirlPresenter;
import com.example.geeknews.view.GirlView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GirlFragment extends BaseFragment<GirlPresenter> implements GirlView {

    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<FuLiBean.ResultsBean> grilList;
    private RlvGirlAdapter adapter;

    public static GirlFragment newInstance() {
        GirlFragment fragment = new GirlFragment();
        return fragment;
    }

    @Override
    protected GirlPresenter initPresenter() {
        return new GirlPresenter();
    }

    @Override
    protected void initView(View inflate) {
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        grilList = new ArrayList<>();
        //mFuliRecyGank.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        //防止图片在上下滑动的时候移动.
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        mRv.setLayoutManager(manager);
        adapter = new RlvGirlAdapter(getContext(),grilList);
        mRv.setAdapter(adapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_meizi;
    }



    @Override
    protected void initData() {
        mPresenter.getGirl();
    }

    @Override
    public void setData(FuLiBean bean) {
        adapter.setData(bean);
    }
}
