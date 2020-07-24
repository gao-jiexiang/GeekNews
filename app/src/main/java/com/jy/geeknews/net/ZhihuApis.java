package com.jy.geeknews.net;

import com.jy.geeknews.bean.DailyNews;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  ZhihuApis {
    String BASE_URL = "http://news-at.zhihu.com/api/4/";

    //得到最新的日报新闻
    @GET("news/latest")
    Observable<DailyNews> getLatestNews();

    //得到旧的日报新闻
    @GET("news/before/{date}")
    Observable<DailyNews> getOldNews(@Path("date") String date);


}
