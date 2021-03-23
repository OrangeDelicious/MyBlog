package com.tianxu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianxu.pojo.Blog;
import com.tianxu.pojo.User;
import com.tianxu.service.BlogService;
import com.tianxu.service.TagService;
import com.tianxu.service.TypeService;
import com.tianxu.vo.BlogQuery;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private TagService tagServiceImpl;


    @Autowired
    private BlogService blogServiceImpl;

    @Autowired
    private TypeService typeServiceImpl;
    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,2);
        List<Blog> blogs = blogServiceImpl.listBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("page",pageInfo);
        model.addAttribute("types",typeServiceImpl.listType());


        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String blogssearch(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, BlogQuery blog, Model model) {
        PageHelper.startPage(pageNum,2);
        List<Blog> blogs = blogServiceImpl.listBlogByTypeAndTags(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("page",pageInfo);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("tags",tagServiceImpl.listTag());
        model.addAttribute("types",typeServiceImpl.listType());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tags",tagServiceImpl.listTag());
        model.addAttribute("types",typeServiceImpl.listType());
        model.addAttribute("blog",blogServiceImpl.getBlog(id));
        return "admin/blogs-input";

    }

    @PostMapping("/blogs")
    private String post(Blog blog, RedirectAttributes attributes, HttpSession session) throws NotFoundException {
        blog.setUser((User) session.getAttribute("user"));
        System.out.println("====================================="+blog);
        blog.setUserId(((User) session.getAttribute("user")).getId());
        blog.setType(typeServiceImpl.getType(blog.getTypeId_my()));
        blog.setTags(tagServiceImpl.listTag(blog.getTagIds()));


        int cnt = 0;
        if (blog.getId() == null) {
//            Integer id = UUID.randomUUID().toString().hashCode();
//            if (id < 0) {
//                id = -id;
//            }
//            blog.setId((long) id);
            cnt = blogServiceImpl.saveBlog(blog);
        } else {
            cnt = blogServiceImpl.updateBlog(blog);
        }
        if (cnt == 0) {
            attributes.addFlashAttribute("message","操作失败");
        } else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    private String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogServiceImpl.deleteBlog(id);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/blogs";
    }
}
