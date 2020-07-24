package com.example.geeknews.base;

public interface BaseView {
    //显示loading
    void showLoading();

    //隐藏loading
    void hideLoading();

    //吐司
    void showToast(String msg);
}
