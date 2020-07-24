package com.example.geeknews.model;


import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.NewsDetailBean;
import com.example.geeknews.net.ApiService;
import com.example.geeknews.net.BaseObserver;
import com.example.geeknews.net.HttpUtil;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.net.RxUtils;

public class NewsDetailModel extends BaseModel {
    public void getDetail(int id, ResultCallBack<NewsDetailBean> callBack) {
        HttpUtil.getInstance()
                .getApiserver(ApiService.sZhihuUrl,ApiService.class)
                .getNewsDetail(id+"")
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NewsDetailBean>(this) {
                    @Override
                    protected void onSuccess(NewsDetailBean newsDetailBean) {
                        callBack.onSuccess(newsDetailBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }
}
