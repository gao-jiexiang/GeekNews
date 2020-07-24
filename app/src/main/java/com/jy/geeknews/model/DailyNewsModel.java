package com.jy.geeknews.model;

import com.jy.geeknews.base.BaseModel;
import com.jy.geeknews.bean.DailyNews;
import com.jy.geeknews.net.BaseObserver;
import com.jy.geeknews.net.DailyNewsCallBack;
import com.jy.geeknews.net.HttpUtils;
import com.jy.geeknews.net.RxUtils;
import com.jy.geeknews.net.ZhihuApis;
import com.jy.geeknews.presenter.DailyNewsPresenter;

import io.reactivex.Observable;

public class DailyNewsModel extends BaseModel {

    //获得最新的日报信息
    public void getLatestNews(DailyNewsCallBack dailyNewsCallBack) {
        ZhihuApis apiserver = HttpUtils.getInstance().getApiserver(ZhihuApis.BASE_URL, ZhihuApis.class);
        Observable<DailyNews> observable = apiserver.getLatestNews();
        //compose 简化线程切换
        observable.compose(RxUtils.<DailyNews>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNews>(this) {
                    @Override
                    protected void onSuccess(DailyNews dailyNews) {
                        dailyNewsCallBack.onSuccess(dailyNews);
                    }

                    @Override
                    protected void error(String err) {

                        dailyNewsCallBack.onFail(err);
                    }
                });
    }

    public void getOldNews(String str, DailyNewsCallBack dailyNewsCallBack) {
        ZhihuApis apiserver = HttpUtils.getInstance().getApiserver(ZhihuApis.BASE_URL, ZhihuApis.class);
        Observable<DailyNews> observable = apiserver.getOldNews(str);
        //compose 简化线程切换
        observable.compose(RxUtils.<DailyNews>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNews>(this) {
                    @Override
                    protected void onSuccess(DailyNews dailyNews) {
                        dailyNewsCallBack.onSuccess(dailyNews);
                    }

                    @Override
                    protected void error(String err) {

                        dailyNewsCallBack.onFail(err);
                    }
                });
    }
}
