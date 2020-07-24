package com.example.geeknews.presenter;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.V2_Base;
import com.example.geeknews.model.JieModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.ui.activity.JieActivity;
import com.example.geeknews.view.JieView;

public class JiePresenter extends BasePresenter<JieView> implements ResultCallBack<V2_Base> {
    private JieModel model;
    public JiePresenter(){
        model=new JieModel();
        addModel(model);
    }

    public void getData(JieActivity jieActivity) {
        model.getJie(jieActivity,this);
    }

    @Override
    public void onSuccess(V2_Base bean) {
        mView.setData(bean);
    }

    @Override
    public void onFail(String msg) {
        mView.showToast(msg);

    }


}
