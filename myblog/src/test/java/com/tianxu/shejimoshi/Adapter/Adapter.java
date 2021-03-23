package com.tianxu.shejimoshi.Adapter;

public class Adapter extends Adaptee implements Target{
    @Override
    public void request() {
        this.myRequest();
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.request();
    }
}
