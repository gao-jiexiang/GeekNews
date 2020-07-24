package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.SectionBean;
import com.example.geeknews.net.ApiService;
import com.example.geeknews.net.BaseObserver;
import com.example.geeknews.net.HttpUtil;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.net.RxUtils;
import com.example.geeknews.presenter.SectionPresenter;

public class SectionModel extends BaseModel {
    public void getNews(ResultCallBack<SectionBean> callBack) {
        ApiService service = HttpUtil.getInstance().getApiserver(ApiService.sSectiUrl, ApiService.class);
        service.getNews()
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SectionBean>(this) {
                    @Override
                    protected void onSuccess(SectionBean sectionBean) {
                        callBack.onSuccess(sectionBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }
}
