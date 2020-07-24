package com.example.geeknews.base;


import java.util.ArrayList;

public class BasePresenter<V extends BaseView> {

    private ArrayList<BaseModel> mModels = new ArrayList<>();
    public V mView;
    public void bindView(V view) {
        this.mView = view;
    }


    //界面销毁的时候收到的通知
    public void onDestory() {
       if (mModels.size()>0){
           for (int i = 0; i < mModels.size(); i++) {
               BaseModel model = mModels.get(i);
               model.onDestory();
           }
       }
    }

    public void addModel(BaseModel model){
        mModels.add(model);
    }

}
