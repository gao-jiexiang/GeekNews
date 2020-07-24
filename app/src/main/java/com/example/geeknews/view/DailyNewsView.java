package com.example.geeknews.view;


import com.example.geeknews.base.BaseView;
import com.example.geeknews.bean.DailyNewsBean;

public interface DailyNewsView extends BaseView {
    void setData(DailyNewsBean bean);
}

