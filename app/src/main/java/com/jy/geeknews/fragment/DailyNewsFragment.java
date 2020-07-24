package com.jy.geeknews.fragment;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.geeknews.CalendarActivity;
import com.jy.geeknews.R;
import com.jy.geeknews.adapter.RlvDailyNewsAdapter;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.base.Constants;
import com.jy.geeknews.bean.DailyNews;
import com.jy.geeknews.presenter.DailyNewsPresenter;
import com.jy.geeknews.util.ToastUtil;
import com.jy.geeknews.view.DailyNewsView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DailyNewsFragment extends BaseFragment<DailyNewsPresenter> implements DailyNewsView {
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private List<DailyNews.TopStoriesBean> mTopStories;
    private List<DailyNews.StoriesBean> mStories;
    private RlvDailyNewsAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new DailyNewsPresenter();
    }

    @Override
    protected void initView() {
        //初始化列表，配置数据：多布局，三个：banner+时间+列表
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlv.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mTopStories = new ArrayList<>();//第一行的banner
        mStories = new ArrayList<>();//第三行开始的列表数据
        mAdapter = new RlvDailyNewsAdapter(getActivity(), mTopStories, mStories);
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getLatestNews();//得到最新的日报新闻
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setDailyNews(DailyNews dailyNews) {
        mTopStories.clear();//清空旧数据
        if(dailyNews.getTop_stories() != null && dailyNews.getTop_stories().size()>0)
            mTopStories.addAll(dailyNews.getTop_stories());

        mStories.clear();//清空旧数据
        if(dailyNews.getStories() != null && dailyNews.getStories().size()>0)
            mStories.addAll(dailyNews.getStories());
        //设置日期信息
        mAdapter.setDate(dailyNews.getDate());

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }

    @OnClick(R.id.fab_calender)
    public void onClick(){
        startActivityForResult(new Intent(getActivity(), CalendarActivity.class),100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 100){
            String str = data.getStringExtra(Constants.DATA);
            mPresenter.getOldNews(str);
        }

    }
}
