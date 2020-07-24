package com.jy.geeknews.presenter;

import com.jy.geeknews.base.BasePresenter;
import com.jy.geeknews.bean.DailyNews;
import com.jy.geeknews.model.DailyNewsModel;
import com.jy.geeknews.net.DailyNewsCallBack;
import com.jy.geeknews.view.DailyNewsView;

public class DailyNewsPresenter extends BasePresenter<DailyNewsView> implements DailyNewsCallBack {

    private DailyNewsModel mDailyNewsModel;

    @Override
    protected void initModel() {
        mDailyNewsModel = new DailyNewsModel();
        addModel(mDailyNewsModel);
    }

    public void getLatestNews() {//最新新闻
        mDailyNewsModel.getLatestNews(this);
    }

    public void getOldNews(String str) {//旧新闻
        mDailyNewsModel.getOldNews(str,this);
    }

    @Override
    public void onSuccess(DailyNews dailyNews) {
        mView.setDailyNews(dailyNews);
    }

    @Override
    public void onFail(String str) {

        mView.showToast(str);
    }


}
