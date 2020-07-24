package com.example.geeknews.presenter;



import androidx.fragment.app.FragmentActivity;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.bean.V2_ItemBean;
import com.example.geeknews.model.V2_ItemModel;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.view.V2ItemView;

import java.util.ArrayList;

import retrofit2.Call;

public class V2Item_Pre extends BasePresenter<V2ItemView> implements ResultCallBack<ArrayList<V2_ItemBean>> {

    private final V2_ItemModel model;

    public V2Item_Pre() {
        model = new V2_ItemModel();
        addModel(model);
    }

    public void getData(FragmentActivity activity, String type) {
        model.getData(activity,this,type);
    }

    @Override
    public void onSuccess(ArrayList<V2_ItemBean> s) {
        mView.setData(s);
    }

    @Override
    public void onFail(String s) {
        mView.showToast(s);
    }
}
