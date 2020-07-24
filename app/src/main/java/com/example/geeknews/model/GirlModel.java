package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.FuLiBean;
import com.example.geeknews.net.ApiService;
import com.example.geeknews.net.BaseObserver;
import com.example.geeknews.net.HttpUtil;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.net.RxUtils;
import com.example.geeknews.presenter.GirlPresenter;

public class GirlModel extends BaseModel {
    public void getGirl(ResultCallBack<FuLiBean> callBack) {
        ApiService service = HttpUtil.getInstance().getApiserver(ApiService.sGirlUrl, ApiService.class);
        service.getGril()
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FuLiBean>(this) {
                    @Override
                    protected void onSuccess(FuLiBean fuLiBean) {
                        callBack.onSuccess(fuLiBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }
}
