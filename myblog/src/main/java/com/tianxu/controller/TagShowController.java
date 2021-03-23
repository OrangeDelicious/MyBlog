package com.tianxu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianxu.pojo.Blog;
import com.tianxu.pojo.Tag;
import com.tianxu.pojo.Type;
import com.tianxu.service.BlogService;
import com.tianxu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagServiceImpl;

    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //这个应该是从大到小排序的，需要你写一下

        List<Tag> tags = tagServiceImpl.listTag();
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        List<Blog> blogs = blogServiceImpl.listBlogByTag(id);

        PageHelper.startPage(pageNum,5);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
