package com.jy.geeknews.bean;

import java.io.Serializable;

public class GoldChannelBean implements Serializable {
    private String name;
    private boolean isChoosed;

    public GoldChannelBean(String name, boolean isChoosed) {
        this.name = name;
        this.isChoosed = isChoosed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    @Override
    public String toString() {
        return "GoldChannelBean{" +
                "name='" + name + '\'' +
                ", isChoosed=" + isChoosed +
                '}';
    }
}
