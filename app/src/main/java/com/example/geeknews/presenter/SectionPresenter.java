package com.example.geeknews.presenter;


import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.SectionBean;
import com.example.geeknews.model.HotModel;
import com.example.geeknews.model.SectionModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.SectionView;

public class SectionPresenter extends BasePresenter<SectionView> implements ResultCallBack<SectionBean> {
    private SectionModel sectionModel;
    public  SectionPresenter(){
        sectionModel = new SectionModel();
        addModel(sectionModel);
    }

    public void getNews(){
        sectionModel.getNews(this);
    }

    @Override
    public void onSuccess(SectionBean bean) {
            if (mView != null){
                if (bean != null){
                    mView.setData(bean);
                }
            }
    }

    @Override
    public void onFail(String msg) {
        if (mView!=null){
            mView.showToast(msg);
        }
    }
}
