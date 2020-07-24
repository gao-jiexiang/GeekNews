package com.jy.geeknews.net;

import com.jy.geeknews.bean.DailyNews;
import com.jy.geeknews.bean.GankBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApis {
    String BASE_URL = "https://gank.io/api/";

    //干货接口
    @GET("data/{tech}/{num}/{page}")
    Observable<GankBean> getGank(@Path("tech") String tech,@Path("num") int num,@Path("page") int page);

}
