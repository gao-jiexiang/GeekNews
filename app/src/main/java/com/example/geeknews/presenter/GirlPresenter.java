package com.example.geeknews.presenter;


import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.FuLiBean;
import com.example.geeknews.model.GirlModel;
import com.example.geeknews.model.HotModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.AboutView;
import com.example.geeknews.view.GirlView;

import java.util.List;

public class GirlPresenter extends BasePresenter<GirlView> implements ResultCallBack<FuLiBean> {
    private GirlModel girlModel;
    public  GirlPresenter(){
        girlModel = new GirlModel();
        addModel(girlModel);
    }
    public void getGirl() {
        girlModel.getGirl(this);
    }


    @Override
    public void onSuccess(FuLiBean bean) {
        if (mView!=null){
            mView.setData(bean);
        }
    }

    @Override
    public void onFail(String msg) {
        if (mView!=null){
            mView.showToast(msg);
        }
    }
}
