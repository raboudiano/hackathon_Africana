package com.Shadows.SpringZ.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import com.Shadows.SpringZ.service.CategoryService;
import com.Shadows.SpringZ.model.Category;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/addCategory")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("CategoryForm", category);
        return "new_category";
    }

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("CategoryForm") Category category) {
        categoryService.createCategory(category);
        return "redirect:/allCategories";
    }

    @RequestMapping("/allCategories")
    public String listCategories(Model model) {
        List<Category> listCategories = categoryService.getAllCategories();
        model.addAttribute("listCategories", listCategories);
        return "liste_categories";
    }

    @RequestMapping("/editCategory")
    public String editCategory(Long id, Model model) {
        Category category = categoryService.getCategoryByID(id);
        model.addAttribute("CategoryForm", category);
        return "new_category";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/allCategories";
    }
}
