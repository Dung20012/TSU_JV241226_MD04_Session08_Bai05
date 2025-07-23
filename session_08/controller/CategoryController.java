package com.data.session_08.controller;

import com.data.session_08.model.entity.Category;
import com.data.session_08.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getCategories(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "") String searchName,
                                Model model) {
        List<Category> categories = categoryService.findAll(page, size, searchName);
        long totalElements = categoryService.countTotalElement(searchName);
        int totalPages = (int) Math.ceil((double) totalElements / size);
        List<Integer> pages = new ArrayList<Integer>();
        for (int i = 1 ; i <= totalPages ; i++) {
            pages.add(i);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("searchName", searchName);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("pages", pages);
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute Category category, Model model , RedirectAttributes redirectAttributes) {
        boolean rs = categoryService.saveCategory(category);
        if(!rs){
            model.addAttribute("category", category);
            model.addAttribute("message","Có lỗi , thêm danh mục không thành công !");
            return "add-category";
        }else {
            redirectAttributes.addFlashAttribute("message","Thêm mới danh mục thành công !");
            return "redirect:/categories";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id); // Cần thêm phương thức findById
        model.addAttribute("category", category);
        return "edit-category";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category, RedirectAttributes redirectAttributes, Model model) {
        boolean rs = categoryService.updateCategory(id, category);
        if(!rs){
            model.addAttribute("category", category);
            model.addAttribute("message","Có lỗi , sửa thông tin không thành công !");
            return "edit-category";
        }else {
            redirectAttributes.addFlashAttribute("message","Cập nhật danh mục thành công !");
            return "redirect:/categories";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id , RedirectAttributes redirectAttributes) {
        boolean rs = categoryService.deleteCategoryById(id);
        if(rs){
            redirectAttributes.addFlashAttribute("message" , "Xóa danh mục thành công !");
        }else {
            redirectAttributes.addFlashAttribute("message" , "Xóa danh mục không thành công !");
        }
        return "redirect:/categories";
    }
}
