package com.example.geeknews.net;


import com.example.geeknews.bean.DailyNewsBean;
import com.example.geeknews.bean.FuLiBean;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.bean.HotBean;
import com.example.geeknews.bean.NewsDetailBean;
import com.example.geeknews.bean.Result_Jie;
import com.example.geeknews.bean.SectionBean;
import com.example.geeknews.bean.VerifyCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";
    String sZhihuUrl = "http://news-at.zhihu.com/api/4/news/";
    String sSectiUrl="http://news-at.zhihu.com/api/4/";
    String sGirlUrl="http://gank.io/api/";
//http://news-at.zhihu.com/api/4/sections
    //http://gank.io/api/data/福利/10/1

    @GET("verify")
    Observable<VerifyCodeBean> getVerify();

    //最新新闻
    @GET("latest")
    Observable<DailyNewsBean> getLastNews();

    //旧新闻
    @GET("before/{date}")
    Observable<DailyNewsBean> getOldNews(@Path("date") String date);

    //热门日报
    @GET("hot")
    Observable<HotBean> getData();

    //专栏
    @GET("sections")
    Observable<SectionBean> getNews();

    @GET("{news_id}")
    Observable<NewsDetailBean> getNewsDetail(@Path("news_id") String news_id);

    //福利
    @GET("data/%E7%A6%8F%E5%88%A9/20/3")
    Observable<FuLiBean> getGril();

    //1)技术文章列表
    @GET("data/{tech}/20/1")
    Observable<GankBean> getGankItem(@Path("tech") String news_id);

    //V2Ex
    String jieUrl="https://www.wanandroid.com/";
    @GET("navi/json")
    Observable<Result_Jie> getJieData();
}
