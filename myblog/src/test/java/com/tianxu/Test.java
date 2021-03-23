package com.tianxu;

import com.tianxu.dao.BlogMapper;
import com.tianxu.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.*;



class Test {



    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {
        CountDownLatch count = new CountDownLatch(1);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(()->{
                try {
                    System.out.println("ok");
                    count.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(2000);
        count.countDown();
        System.out.println("dou ok");
        poolExecutor.shutdown();
    }
}
