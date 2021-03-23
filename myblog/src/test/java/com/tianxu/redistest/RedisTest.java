package com.tianxu.redistest;

import com.alibaba.fastjson.JSON;
import com.tianxu.pojo.Blog;
import com.tianxu.pojo.Type;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

public class RedisTest {
    public static void main(String[] args) {
//        Blog blog = new Blog();
//        blog.setId(1L);
//
//        Type type1 = new Type();
//        type1.setId(200L);
//        type1.setName("机器学习");
//        blog.setType(type1);
//        Type type2 = new Type();
//        type2.setId(100L);
//        type2.setName("原创");
//        List<Blog> blogs = new ArrayList<>();
//        blogs.add(blog);
//        Blog blog1 = new Blog();
//        blog1.setId(2L);
//        blog1.setType(type2);
//        blogs.add(blog1);
//        System.out.println(blogs);
//        String now = JSON.toJSONString(blogs);
//        System.out.println(now);
//        List<Blog> blogs1 = (List<Blog>) JSON.parseArray(now,Blog.class);
//        System.out.println(blogs1);
//
        Jedis jedis = new Jedis();
//        jedis.set("blogs",now);
//        String now2 = jedis.get("blogs");
//        System.out.println(now2);
//        List<Blog> blogs2 = (List<Blog>) JSON.parseArray(now2,Blog.class);
//        System.out.println(blogs2);

        String test = jedis.get("ss");
        System.out.println(test);
    }
}
