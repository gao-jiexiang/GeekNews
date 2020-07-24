package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.HotBean;
import com.example.geeknews.net.ApiService;
import com.example.geeknews.net.BaseObserver;
import com.example.geeknews.net.HttpUtil;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.net.RxUtils;

public class HotModel extends BaseModel {
    public void getData(ResultCallBack<HotBean> callBack) {
        ApiService service = HttpUtil.getInstance().getApiserver(ApiService.sZhihuUrl, ApiService.class);
        service.getData()
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HotBean>(this) {
                    @Override
                    protected void onSuccess(HotBean hotBean) {
                        callBack.onSuccess(hotBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }
}
