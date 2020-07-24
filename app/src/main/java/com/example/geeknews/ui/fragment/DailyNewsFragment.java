package com.example.geeknews.ui.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvDailyNewsAdapter;
import com.example.geeknews.base.BaseApp;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseRlvAdapter;
import com.example.geeknews.base.Constants;
import com.example.geeknews.bean.DailyNewsBean;
import com.example.geeknews.bean.SectionBean;
import com.example.geeknews.presenter.DailyNewsPresenter;
import com.example.geeknews.ui.activity.CalendarActivity;
import com.example.geeknews.ui.activity.NewsDetailActivity;
import com.example.geeknews.util.LogUtil;
import com.example.geeknews.util.SystemUtil;
import com.example.geeknews.util.TimeUtil;
import com.example.geeknews.view.DailyNewsView;
import com.example.geeknews.widget.ColorDividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class DailyNewsFragment extends BaseFragment<DailyNewsPresenter> implements DailyNewsView {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.fabs)
    FloatingActionButton fabs;
    private RlvDailyNewsAdapter adapter;

    public static BaseFragment newInstance() {
        DailyNewsFragment fragment = new DailyNewsFragment();

        return fragment;
    }

    @Override
    protected DailyNewsPresenter initPresenter() {
        return new DailyNewsPresenter();
    }

    @Override
    protected void initView(View inflate) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<DailyNewsBean.StoriesBean> newsList = new ArrayList<>();
        ArrayList<DailyNewsBean.TopStoriesBean> bannerList = new ArrayList<>();
        adapter = new RlvDailyNewsAdapter(getContext(), newsList, bannerList);
        rlv.setAdapter(adapter);

        rlv.addItemDecoration(new ColorDividerItemDecoration(
                BaseApp.getRes().getColor(R.color.c_eaeaea),
                SystemUtil.dp2px(10)));

        adapter.setOmItemClickListener(new BaseRlvAdapter.OmItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                go2Detail(adapter.mNewsList.get(position));
            }
        });



    }

    private void go2Detail(DailyNewsBean.StoriesBean bean) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra(Constants.DATA, bean);
        startActivity(intent);
    }

    @Override
    protected void initData() {
        mPresenter.getData("");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dailynews;
    }


    @Override
    public void setData(DailyNewsBean bean) {
        adapter.setData(bean);
    }

    @Override
    protected void initListener() {
    }

    @OnClick({R.id.fabs})
    public  void click(View view){
        switch (view.getId()){
            case R.id.fabs:
                go2Calendar();
                break;
        }
    }

    private void go2Calendar() {
        //startActivityForResult();
        //EnventBus
        startActivity(new Intent(getContext(), CalendarActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe()
    public void receiveDate(CalendarDay day){
        LogUtil.print("年:"+day.getYear()+",月:"+day.getMonth()
                +",日:"+day.getDay());
        //m层中如果是需要最新新闻,传"",旧的新闻,传日期
        String date = "";
        /*if (day是今日的日期){
            date = "";
        }else {
            date = "day对应的字符串日期";
        }*/

        //获取当前日期
        Calendar current = Calendar.getInstance();
        Calendar select = day.getCalendar();

        String strCurrent = TimeUtil.parseTime(current);
        String strSelect = TimeUtil.parseTime(select);
        if (strCurrent.equals(strSelect)){
            date = "";
        }else {
            date = strSelect;
        }
        LogUtil.print("date:"+date);
        //旧新闻给定0302,返回的是0301的新闻,后端接口有问题,如果是旧日期我们需要手动给添加一天
        if (!TextUtils.isEmpty(date)){
            select.add(Calendar.DAY_OF_MONTH,1);
            //添加一天后重新复制
            date = TimeUtil.parseTime(select);
        }
        mPresenter.getData(date);
    }



}
