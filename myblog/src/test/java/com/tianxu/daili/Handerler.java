package com.tianxu.daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Handerler implements InvocationHandler {


    private Object target;
    private Advice advice;

    public Handerler(Object target, Advice advice) {
        this.target = target;
        this.advice = advice;
    }

    private Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("zhiqian");
        advice.seeHouse();
        Object result = method.invoke(target,args);
        System.out.println("zhihou");
        advice.hetong();
        return result;
    }


    public static void main(String[] args) {
        Handerler handerler = new Handerler(new SmsServiceImpl(), new Advice());
        SmsService s = (SmsService) handerler.getProxy();
        s.send("nihao");
    }
}
