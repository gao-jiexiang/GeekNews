package com.example.geeknews.bean;

import java.io.Serializable;

public class V2_Tab implements Serializable {
    private String url;
    private String name;
public boolean isSelect;

    public V2_Tab(String url, String name, boolean isSelect) {
        this.url = url;
        this.name = name;
        this.isSelect = isSelect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
