package com.jy.geeknews.bean;

public class Car {
    private String name;
    private String headerName;

    public Car(String name, String headerName) {
        this.name = name;
        this.headerName = headerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}
