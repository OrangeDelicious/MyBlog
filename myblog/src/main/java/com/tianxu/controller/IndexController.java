package com.tianxu.controller;

import com.fasterxml.jackson.core.TreeNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianxu.pojo.Blog;
import com.tianxu.service.BlogService;
import com.tianxu.service.TagService;
import com.tianxu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Controller
public class IndexController {


    @Autowired
    private BlogService blogServiceImpl;


    @Autowired
    private TypeService typeServiceImpl;
    @Autowired
    private TagService tagServiceImpl;
    @GetMapping("/")
    public String index(Model model,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogServiceImpl.listBlog();
        System.out.println(blogs.get(0).getUpdateTime());
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("page",pageInfo);
//        for (Blog b : blogs) {
//            System.out.println("----------------->"+b);
//        }

        model.addAttribute("types",typeServiceImpl.listTypeTop(6));
        model.addAttribute("tags",tagServiceImpl.listTagTop(10));
//        model.addAttribute("recommendBlogs",blogServiceImpl.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, String query) {
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogServiceImpl.listBlogByContent(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("page",pageInfo);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog blog = blogServiceImpl.getBlog(id);
        blogServiceImpl.updateView(id,blog.getViews()+1);
        model.addAttribute("blog",blog);
        return "blog";
    }


}
