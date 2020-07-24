package com.example.geeknews.net;

import com.example.geeknews.bean.VerifyCodeBean;

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String msg);
}
