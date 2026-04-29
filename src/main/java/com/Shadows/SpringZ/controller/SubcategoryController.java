package com.Shadows.SpringZ.controller;

import com.Shadows.SpringZ.model.Subcategory;
import com.Shadows.SpringZ.service.CategoryService;
import com.Shadows.SpringZ.service.SubcategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubcategoryController {

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    public SubcategoryController(SubcategoryService subcategoryService, CategoryService categoryService) {
        this.subcategoryService = subcategoryService;
        this.categoryService = categoryService;
    }

    @GetMapping("/addSubCategory")
    public String addSubCategory(Model model) {
        model.addAttribute("SubCategoryFrom", new Subcategory());
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "new_subcategory";
    }

    @PostMapping("/saveSubCategory")
    public String saveSubCategory(@ModelAttribute("SubCategoryFrom") Subcategory subcategory) {
        subcategoryService.createSubcategory(subcategory);
        return "redirect:/allSubCategories";
    }

    @GetMapping("/allSubCategories")
    public String listSubCategories(Model model) {
        List<Subcategory> listSubCategories = subcategoryService.getAllSubcategories();
        model.addAttribute("listsubcategories", listSubCategories);
        return "liste_subcategories";
    }

    @GetMapping("editSubcategory/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Subcategory subcategory = subcategoryService.getSubcategoryByID(id);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("categoriesList", categoryService.getAllCategories());
        return "update_subcategory";
    }

    @PostMapping("updatesubcategory/{id}")
    public String updateSubcategory(
            @PathVariable("id") long id,
            Subcategory subcategory,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            subcategory.setId(id);
            return "update_subcategory";
        }

        subcategoryService.updateSubcategory(subcategory);
        return "redirect:/allSubCategories";
    }

    @GetMapping("deleteSubCategories/{id}")
    public String deleteSubcategory(@PathVariable("id") long id) {
        subcategoryService.deleteSubcategory(id);
        return "redirect:/allSubCategories";
    }
}
