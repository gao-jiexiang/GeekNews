package com.example.geeknews.model;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.V2_ItemBean;
import com.example.geeknews.bean.V2_Tab;
import com.example.geeknews.net.ResultCallBack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.geeknews.presenter.V2Item_Pre;
import com.example.geeknews.presenter.V2exPresenter;

import java.io.IOException;
import java.util.ArrayList;

public class V2_ItemModel extends BaseModel {
    String mUrl="https://www.v2ex.com/";
    private String src;
    private String count;
    private String title;
    private Element topicElement;
    private String second;
    private ArrayList<V2_Tab> tabs;
    private ArrayList<String> arrayList1;
    public void getTab(FragmentActivity activity, ResultCallBack<ArrayList<V2_Tab>> callBack ) {
        tabs = new ArrayList<>();
        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(mUrl).get();
                    Element first = document.select("div#Tabs").first();
                    String s = first.toString();
                    Log.d("网络数据", "initView: " + s);
                    Elements elements = first.select("a");
                    for (int i = 0; i < elements.size(); i++) {
                        String href = elements.get(i).attr("href");
                        String text = elements.get(i).text();
                        tabs.add(new V2_Tab(href, text,true));
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(tabs);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.onFail(e.getMessage());
                }
            }
        }).start();

    }

    public void getData(FragmentActivity activity, ResultCallBack<ArrayList<V2_ItemBean>> callBack, String type) {
        final ArrayList<V2_ItemBean> beans = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Document doc = Jsoup.connect(mUrl+type).get();

                    Elements items = doc.select("div.cell.item");
                    if (items != null && items.size()>0){
                        for (Element element :items) {
                            Element image = element.select("table tbody tr td a img.avatar").first();
                            src = image.attr("src");
                            Element commentCount = element.select("table tbody tr td a.count_livid").first();
                            if (commentCount != null){
                                count = commentCount.text();
                            }
                            Element titleElement = element.select("table tbody tr td span.item_title a").first();
                            if (titleElement != null){
                                title = titleElement.text();
                            }
                            //二级tab+作者+最后回复人+发布时间
                            topicElement = element.select("table tbody tr td span.topic_info").first();
                            if (topicElement !=null){
                                second = topicElement.text();
                            }
                            beans.add(new V2_ItemBean(src,title,count,second));
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(beans);

                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.onFail(e.getMessage());
                }
            }
        }).start();
    }
}
