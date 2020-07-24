package com.example.geeknews.presenter;


import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.DailyNewsBean;
import com.example.geeknews.model.DailNewsModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.DailyNewsView;

public class DailyNewsPresenter extends BasePresenter<DailyNewsView> {
    private DailNewsModel dailyNewsModel;
    public  DailyNewsPresenter(){
        dailyNewsModel = new DailNewsModel();
        addModel(dailyNewsModel);
    }
    public void getData(String date) {
        dailyNewsModel.getData(date, new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean bean) {
                if (bean != null && mView != null){
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
