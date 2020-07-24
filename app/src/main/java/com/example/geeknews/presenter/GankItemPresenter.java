package com.example.geeknews.presenter;


import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.model.GankItemModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.AboutView;
import com.example.geeknews.view.GankItemView;

public class GankItemPresenter extends BasePresenter<GankItemView> {
    private GankItemModel gankItemModel;
    public  GankItemPresenter(){
        gankItemModel=new GankItemModel();
        addModel(gankItemModel);
    }

    public void getData(String tech) {
        gankItemModel.getDate(tech,new ResultCallBack<GankBean>() {

            @Override
            public void onSuccess(GankBean bean) {
                if (bean!=null&&mView!=null){
                    mView.setData(bean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView!=null){
                    mView.showToast(msg);
                }
            }
        });
    }

}
