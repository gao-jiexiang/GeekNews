package com.jy.geeknews.net;

import com.jy.geeknews.bean.GankBean;

public interface GankCallBack {
    void onSuccess(GankBean gankBean);
    void onFail(String str);
}
