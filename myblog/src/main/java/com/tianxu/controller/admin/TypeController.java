package com.tianxu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianxu.pojo.Tag;
import com.tianxu.pojo.Type;
import com.tianxu.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeServiceImpl;

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,2);
        List<Type> list = typeServiceImpl.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("page", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input() {
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeServiceImpl.getType(id));
        return "admin/types-update";
    }


    @PostMapping("/types/update")
    public String editInput(Type type, RedirectAttributes attributes) {
        typeServiceImpl.updateType(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    private String deleteType(@PathVariable Long id,RedirectAttributes attributes) {
        typeServiceImpl.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }


    @PostMapping("/types/add")
    public String post(Type type, RedirectAttributes attributes) {
        Type t_same = typeServiceImpl.getTypeByName(type.getName());

        if (t_same != null) {
            attributes.addFlashAttribute("message","已有该分类");
        } else {
            typeServiceImpl.saveType(type);
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }

}
