package com.example.geeknews.presenter;


import androidx.fragment.app.FragmentActivity;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.V2_Tab;
import com.example.geeknews.model.V2_ItemModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.V2exView;

import java.util.ArrayList;

public class V2exPresenter extends BasePresenter<V2exView> implements ResultCallBack<ArrayList<V2_Tab>> {
    private V2_ItemModel model;
    public  V2exPresenter(){
        model=new V2_ItemModel();
        addModel(model);
    }
    public void getData(FragmentActivity activity) {
        model.getTab(activity,this);
    }

    @Override
    public void onSuccess(ArrayList<V2_Tab> bean) {
        mView.setTab(bean);
    }

    @Override
    public void onFail(String msg) {
        mView.showToast(msg);
    }
}
