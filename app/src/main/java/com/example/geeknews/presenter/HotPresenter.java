package com.example.geeknews.presenter;


import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.HotBean;
import com.example.geeknews.model.DailNewsModel;
import com.example.geeknews.model.HotModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.HotView;

public class HotPresenter extends BasePresenter<HotView> implements ResultCallBack<HotBean> {
    private HotModel hotModel;
    public  HotPresenter(){
        hotModel = new HotModel();
        addModel(hotModel);
    }

    public void getData(){
        hotModel.getData(this);
    }

    @Override
    public void onSuccess(HotBean bean) {
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
