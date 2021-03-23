package com.tianxu.shejimoshi.Prototype;

public class Teacher {

    public static void main(String[] args) {
        ProtoType protoType = new ProtoType();
        protoType.setClasses(1);
        protoType.setGrade(1);
        for (int i = 0; i < 20; i++) {
            int num = i;
            new Thread(()->{
                try {
                    ProtoType student = protoType.clone();
                    student.setNum(num);
                    System.out.println(student.toString());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
