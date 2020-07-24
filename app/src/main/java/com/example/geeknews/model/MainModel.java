package com.example.geeknews.model;



import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.VerifyCodeBean;
import com.example.geeknews.net.ApiService;
import com.example.geeknews.net.BaseObserver;
import com.example.geeknews.net.HttpUtil;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.net.RxUtils;

//优化
//1.封装了网络框架
//2.优化了切换线程
//3.优化observer,a.不必要复写的方法不想再复写了
//               b.在网络请求未完成的情况下,如果用户推出界面,需要停止网络请求,并且打断观察者和被观察者的订阅关系
public class MainModel extends BaseModel {
    //private Disposable mD;

    public void getVerifyCode(ResultCallBack<VerifyCodeBean> callBack) {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getVerify()
                .subscribeOn(Schedulers.io())//被观察者运行的线程,子线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //当发生订阅的时候首先会走这个方法
                        //Disposable
                        //d.dispose();用来打断订阅关系的,取消网络请求
                    }

                    @Override
                    public void onNext(VerifyCodeBean verifyCodeBean) {
                        //成功
                        callBack.onSuccess(verifyCodeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //失败的方法
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        //事件完成的方法
                    }
                });*/


        /*ApiService apiService = HttpUtil
                .getInstance()
                .getApiserver(ApiService.sBaseUrl, ApiService.class);

        apiService.getVerify()
                *//*.subscribeOn(Schedulers.io())//被观察者运行的线程,子线程
                .observeOn(AndroidSchedulers.mainThread())*//*
                .compose(RxUtils.rxObserableSchedulerHelper())//切换线程,观察者的主线程以及被观察者的子线程
                .subscribe(new Observer<VerifyCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //当发生订阅的时候首先会走这个方法
                        //Disposable
                        //d.dispose();用来打断订阅关系的,取消网络请求
                        //mD = d;
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(VerifyCodeBean verifyCodeBean) {
                        //成功
                        callBack.onSuccess(verifyCodeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //失败的方法
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        //事件完成的方法
                    }
                });*/




        HttpUtil.getInstance()
                .getApiserver(ApiService.sBaseUrl, ApiService.class)
                .getVerify()
                .compose(RxUtils.rxObserableSchedulerHelper())//切换线程,观察者的主线程以及被观察者的子线程
                .subscribe(new BaseObserver<VerifyCodeBean>(this) {
                    @Override
                    protected void onSuccess(VerifyCodeBean verifyCodeBean) {
                        //成功
                        callBack.onSuccess(verifyCodeBean);
                    }

                    @Override
                    protected void error(String e) {
                        //失败的方法
                        callBack.onFail(e.toString());
                    }
                });
    }

    //在网络请求未完成的情况下,如果用户推出界面,需要停止网络请求,并且打断观察者和被观察者的订阅关系
    /*public void onDestory() {
       if (mD != null){
           mD.dispose();
       }
    }*/
}
