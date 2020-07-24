package com.example.geeknews.view;

import com.example.geeknews.base.BaseView;
import com.example.geeknews.bean.V2_ItemBean;

import java.util.ArrayList;

public interface V2ItemView extends BaseView {
    void setData(ArrayList<V2_ItemBean> s);
}
