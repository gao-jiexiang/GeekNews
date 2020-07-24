package com.example.geeknews.presenter;


import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.NewsDetailBean;
import com.example.geeknews.model.NewsDetailModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.NewsDetailView;

public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    private final NewsDetailModel mNewsDetailModel;

    public NewsDetailPresenter(){
        mNewsDetailModel = new NewsDetailModel();
        addModel(mNewsDetailModel);
    }
    public void getDetail(int id) {
        mNewsDetailModel.getDetail(id, new ResultCallBack<NewsDetailBean>() {
            @Override
            public void onSuccess(NewsDetailBean bean) {
                if (bean != null && mView !=null){
                    mView.setData(bean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView != null){
                    mView.showToast(msg);
                }
            }
        });
    }
}
