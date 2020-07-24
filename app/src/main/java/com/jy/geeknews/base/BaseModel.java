package com.jy.geeknews.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {//Model的基类，做一些共同的处理：把网络请求对象加入到rxjava处理集，统一保存和销毁
    ////这也是一个容器,Rxjava专门写的用来放Disposable,调用dispose()可以把容器所有的
    //Disposable 的网络请求和订阅关系取消
    private CompositeDisposable mCompositeDisposable = null;

    ////由M层自己把网络和订阅关系保存起来，便于取消
    public void addDisposable(Disposable disposable){//收集本model的Disposable
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }

    ////由M层自己把网络和订阅关系取消
    public void destroy(){
        if (mCompositeDisposable != null)
            mCompositeDisposable.dispose();
    }

    public void removeDisposable(Disposable disposable) {
        if (mCompositeDisposable != null){
            mCompositeDisposable.remove(disposable);
        }
    }
}
