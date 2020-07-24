package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.net.ApiService;
import com.example.geeknews.net.BaseObserver;
import com.example.geeknews.net.HttpUtil;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.net.RxUtils;

public class GankItemModel extends BaseModel {

    public void getDate(String tech, ResultCallBack<GankBean> callBack) {
        ApiService service = HttpUtil.getInstance().getApiserver(ApiService.sGirlUrl, ApiService.class);
        service.getGankItem(tech)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<GankBean>(this) {
                    @Override
                    protected void onSuccess(GankBean gankBean) {
                        callBack.onSuccess(gankBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }
}
