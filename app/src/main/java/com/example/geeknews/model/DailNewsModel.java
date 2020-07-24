package com.example.geeknews.model;

import android.text.TextUtils;

import io.reactivex.Observable;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.DailyNewsBean;
import com.example.geeknews.net.ApiService;
import com.example.geeknews.net.BaseObserver;
import com.example.geeknews.net.HttpUtil;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.net.RxUtils;

public class DailNewsModel extends BaseModel {

    public void getData(String date, ResultCallBack<DailyNewsBean> callBack) {
        ApiService service = HttpUtil.getInstance().getApiserver(ApiService.sZhihuUrl, ApiService.class);
        Observable<DailyNewsBean> news;
        if (TextUtils.isEmpty(date)) {
            //如果日期是"",那么代表是要最新新闻
            news= service.getLastNews();
        }else {
            //如果不为空,请求给定日期的新闻
            news =  service.getOldNews(date);
        }

        news.compose(RxUtils.rxObserableSchedulerHelper())
               .subscribe(new BaseObserver<DailyNewsBean>(this) {
                   @Override
                   protected void onSuccess(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
                   }

                   @Override
                   protected void error(String err) {
                        callBack.onFail(err);
                   }
               });
    }
}
