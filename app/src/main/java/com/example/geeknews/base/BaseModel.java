package com.example.geeknews.base;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {

    //private ArrayList<Disposable> mDisposables = new ArrayList<>();
    //rx 底层已经提供放置disposable对象的容器了
    CompositeDisposable mCompositeDisposable = null;

    public void addDisposable(Disposable d){
        if (mCompositeDisposable == null){
            mCompositeDisposable =new CompositeDisposable();
        }

        mCompositeDisposable.add(d);
    }

    //收到界面销毁的通知
    public void onDestory() {
        /*if (mD != null){
            mD.dispose();
        }*/
        if (mCompositeDisposable != null){
            //这个方法会遍历容器中所有的disposable对象,会帮我们调用dispose()
            mCompositeDisposable.dispose();
        }
    }

    public void removeDisposable(Disposable d) {
        if (mCompositeDisposable == null){
            return;
        }

        mCompositeDisposable.remove(d);
    }
}
