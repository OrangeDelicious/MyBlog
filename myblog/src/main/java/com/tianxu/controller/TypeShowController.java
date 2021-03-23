package com.tianxu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianxu.pojo.Blog;
import com.tianxu.service.BlogService;
import com.tianxu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.tianxu.pojo.Type;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //这个应该是从大到小排序的，需要你写一下

        List<Type> types = typeServiceImpl.listType();
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types",types);
        List<Blog> blogs = blogServiceImpl.listBlogByType(id);

        PageHelper.startPage(pageNum,5);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
