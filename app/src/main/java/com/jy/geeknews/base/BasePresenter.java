package com.jy.geeknews.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {//V表示泛型（不固定的类型） 只要继承了BaseView都可以接受
    public V mView;//每一个p要有一个view，view的类别必须是BaseView的后代，此处不知道具体是哪个V
    public ArrayList<BaseModel> mModels = new ArrayList<>();////model的集合收集本页面的所有的model（一个页面的数据可能会有多个来源）
    public BasePresenter(){
        //给v赋值，给m赋值，此处不再给v赋值，有点太早了，后面合适的时候通过bindView赋值，
        initModel();
    }
    public void bindView(V view){//绑定mView
        mView = view;
    }

    protected abstract void initModel();//由子类实现，创建具体的合适的model的对象
    public void addModel(BaseModel baseModel){
        mModels.add(baseModel);
    }

    //优化
    public void destroy() {
        //页面销毁时，把p和v和m的关系解除
        mView = null;
        //把model的请求销毁
        for (int i = 0; i < mModels.size(); i++) {
            BaseModel baseModel = mModels.get(i);
            baseModel.destroy();
            baseModel = null;
        }
    }
}
