package com.example.geeknews.bean;

public class V2_ItemBean {
    private String image;
    private String title;
    private String  number;
    private String  secondTab;

    public V2_ItemBean(String image, String title, String number, String secondTab) {
        this.image = image;
        this.title = title;
        this.number = number;
        this.secondTab = secondTab;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSecondTab() {
        return secondTab;
    }

    public void setSecondTab(String secondTab) {
        this.secondTab = secondTab;
    }
}
