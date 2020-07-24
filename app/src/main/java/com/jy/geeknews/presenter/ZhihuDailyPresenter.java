package com.jy.geeknews.presenter;

import com.jy.geeknews.base.BasePresenter;
import com.jy.geeknews.model.ZhihuDailyModel;
import com.jy.geeknews.view.ZhihuDailyView;

public class ZhihuDailyPresenter extends BasePresenter<ZhihuDailyView> {
    private ZhihuDailyModel mZhihuDailyModel;

    @Override
    protected void initModel() {
        mZhihuDailyModel = new ZhihuDailyModel();
        addModel(mZhihuDailyModel);
    }
}
