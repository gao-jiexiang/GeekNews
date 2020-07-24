package com.jy.geeknews.model;

import com.jy.geeknews.base.BaseModel;
import com.jy.geeknews.bean.DailyNews;
import com.jy.geeknews.bean.GankBean;
import com.jy.geeknews.net.BaseObserver;
import com.jy.geeknews.net.DailyNewsCallBack;
import com.jy.geeknews.net.GankApis;
import com.jy.geeknews.net.GankCallBack;
import com.jy.geeknews.net.HttpUtils;
import com.jy.geeknews.net.RxUtils;
import com.jy.geeknews.net.ZhihuApis;

import io.reactivex.Observable;

public class GankDetailModel extends BaseModel {

    //获得最新的日报信息
    public void getGank(String tech,int num,int page,GankCallBack gankCallBack) {
        GankApis apiserver = HttpUtils.getInstance().getApiserver(GankApis.BASE_URL, GankApis.class);
        Observable<GankBean> observable = apiserver.getGank(tech,num,page);
        //compose 简化线程切换
        observable.compose(RxUtils.<GankBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<GankBean>(this) {
                    @Override
                    protected void onSuccess(GankBean gankBean) {
                        gankCallBack.onSuccess(gankBean);
                    }

                    @Override
                    protected void error(String err) {

                        gankCallBack.onFail(err);
                    }
                });
    }
}
