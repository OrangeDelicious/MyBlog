package com.tianxu.daili;

public class SmsServiceImpl implements SmsService{



    @Override
    public String send(String message) {
        System.out.println(message);
        return message;
    }


}
