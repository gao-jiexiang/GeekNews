package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.V2_Base;
import com.example.geeknews.net.ResultCallBack;
import com.example.geeknews.presenter.JiePresenter;
import com.example.geeknews.ui.activity.JieActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class JieModel extends BaseModel {
    String mUrl="https://www.v2ex.com/";
    private ArrayList<String> arrayList1=new ArrayList<>();
    private ArrayList<ArrayList<String>> arrayLists=new ArrayList<>();
    private ArrayList<String> arrayList=new ArrayList<>();
    public void getJie(JieActivity jieActivity, ResultCallBack<V2_Base> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document = null;

                try {
                    document=Jsoup.connect(mUrl).get();
                    Element first = document.select("div#Main").first();
                    Elements select1 = first.select("div.box");
                    Element element1 = select1.get(1);
                    Elements select2 = element1.select("div.cell");
                    Elements select = document.select("div.cell");
                    Elements selectSpan = select.select("table tbody tr td span.fade");
                    Elements table = select2.select("table");
                    for (int i = 0; i < table.size(); i++) {
                        Element element = table.get(i);
                        Elements text = element.select("tbody tr td a");
                        arrayList1 = new ArrayList<>();
                        if (!text.text().isEmpty()){
                            arrayList1.add(text.text());
                        }
                        arrayLists.add(arrayList1);
                    }

                    for (int i = 0; i < selectSpan.size(); i++) {
                        Element element = selectSpan.get(i);
                        String text = element.text();
                        arrayList.add(text);
                    }

                    jieActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(new V2_Base(arrayLists,arrayList));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    jieActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFail(e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }
}
