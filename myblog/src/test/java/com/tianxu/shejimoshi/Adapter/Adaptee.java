package com.tianxu.shejimoshi.Adapter;

public class Adaptee {
    public void myRequest() {
        System.out.println("实际调用");
    }
    //但是我被适配者提供了一个 myRequest()方法
}
