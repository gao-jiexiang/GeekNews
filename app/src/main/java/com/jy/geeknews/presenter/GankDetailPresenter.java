package com.jy.geeknews.presenter;

import com.jy.geeknews.base.BasePresenter;
import com.jy.geeknews.model.GankDetailModel;
import com.jy.geeknews.view.GankDetailView;

public class GankDetailPresenter extends BasePresenter<GankDetailView> {

    private GankDetailModel mGankDetailModel;

    @Override
    protected void initModel() {
        mGankDetailModel = new GankDetailModel();
        addModel(mGankDetailModel);
    }

}
