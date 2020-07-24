package com.example.geeknews.bean;

public class Car {
    public  String name;
    public String header;

    public Car(String name, String header) {
        this.name = name;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public Car setName(String name) {
        this.name = name;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public Car setHeader(String header) {
        this.header = header;
        return this;
    }
}
