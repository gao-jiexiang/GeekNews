package com.jy.geeknews.presenter;

import com.jy.geeknews.base.BasePresenter;
import com.jy.geeknews.model.EmptyModel;
import com.jy.geeknews.view.EmptyView;

public class EmptyPresenter extends BasePresenter<EmptyView> {

    private EmptyModel mEmptyModel;

    @Override
    protected void initModel() {
        mEmptyModel = new EmptyModel();
        addModel(mEmptyModel);
    }
}
