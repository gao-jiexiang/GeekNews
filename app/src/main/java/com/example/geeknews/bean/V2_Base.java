package com.example.geeknews.bean;

import java.util.ArrayList;

public class V2_Base {
    private ArrayList<ArrayList<String>> item;
    private ArrayList<String> title;

    public V2_Base(ArrayList<ArrayList<String>> item, ArrayList<String> title) {
        this.item = item;
        this.title = title;
    }

    public ArrayList<ArrayList<String>> getItem() {
        return item;
    }

    public void setItem(ArrayList<ArrayList<String>> item) {
        this.item = item;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }
}
