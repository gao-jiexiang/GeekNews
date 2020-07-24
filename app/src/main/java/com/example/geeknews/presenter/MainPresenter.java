package com.example.geeknews.presenter;


import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.VerifyCodeBean;
import com.example.geeknews.model.MainModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    private final MainModel mMainModel;

    public MainPresenter(){
        mMainModel = new MainModel();
        addModel(mMainModel);
    }
    public void getVerifyCode() {
        mMainModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean != null && bean.getCode() == 200){
                    if (mView != null){
                        mView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

/*    public void onDestory() {
        mMainModel.onDestory();
    }*/
}
