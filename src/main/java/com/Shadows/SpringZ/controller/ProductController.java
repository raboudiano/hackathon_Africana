package com.Shadows.SpringZ.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import com.Shadows.SpringZ.service.ProductService;
import com.Shadows.SpringZ.model.Product;
import com.Shadows.SpringZ.model.Provider;
import com.Shadows.SpringZ.model.Subcategory;
import com.Shadows.SpringZ.service.ProviderService;
import com.Shadows.SpringZ.service.SubcategoryService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("ProductForm", product);
        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("ProductForm") Product product) {
        productService.createProduct(product);
        return "redirect:/all";
    }

    @RequestMapping("/all")
    public String listProducts(Model model) {
        List<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "liste_products";
    }

    @RequestMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductByID(id);
        model.addAttribute("ProductForm", product);
        return "new_product";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/all";
    }
}
