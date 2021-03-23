package com.tianxu.shejimoshi.Template;

public abstract class Computer {
    public abstract void use();

    public void open() {
        System.out.println("开机");
    }
    public void close() {
        System.out.println("关机");
    }
    final void useComputer() {
        open();
        use();
        close();
    }
}
