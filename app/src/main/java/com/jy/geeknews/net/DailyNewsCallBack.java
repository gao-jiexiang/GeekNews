package com.jy.geeknews.net;

import com.jy.geeknews.bean.DailyNews;

public interface DailyNewsCallBack {
    void onSuccess(DailyNews dailyNews);
    void onFail(String str);
}
