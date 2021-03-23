package com.tianxu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianxu.pojo.Blog;
import com.tianxu.pojo.Tag;
import com.tianxu.service.TagService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagServiceImpl;

    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,2);
        List<Tag> tags = tagServiceImpl.listTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("page", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input() {
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagServiceImpl.getTag(id));
        return "admin/tags-update";
    }


    @PostMapping("/tags/update")
    public String editInput(Tag tag, RedirectAttributes attributes) {
        int t = tagServiceImpl.updateTag(tag);
        attributes.addFlashAttribute("message","修改成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    private String deletetag(@PathVariable Long id,RedirectAttributes attributes) {
        tagServiceImpl.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/add")
    public String post(Tag tag, RedirectAttributes attributes) {
        Tag t_same = tagServiceImpl.getTagByName(tag.getName());

        if (t_same != null) {
            attributes.addFlashAttribute("message","已有该分类");
        } else {
            int t = tagServiceImpl.saveTag(tag);
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";
    }

}
