package com.niantic.controllers;

import com.niantic.models.Categories;
import com.niantic.models.Vendors;
import com.niantic.services.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class CategoryController
{
    private CategoryDao categoryDao = new CategoryDao();
    
    @GetMapping("/categories")
    public String getAllCategories(Model model)
    {
        ArrayList<Categories> categories;
        categories = categoryDao.getCategories();
        model.addAttribute("categories", categories);
        return "categories/index";
    }

    @GetMapping("/categories/add")
    public String addCategory(Model model)
    {
        model.addAttribute("categories", new Categories());
        model.addAttribute("action", "add");
        return "categories/add_edit";
    }

    @PostMapping("/categories/add")
    public String addCategory(Model model, @ModelAttribute("categories") Categories category)
    {
        categoryDao.addCategory(category);
        model.addAttribute("categories", category);
        return "categories/add_success";
    }

    @GetMapping("/categories/{id}/delete")
    public String deleteCategory(Model model, @PathVariable int id)
    {
        var category = categoryDao.getCategoryById(id);
        model.addAttribute(category);

        return "categories/delete";
    }

    @PostMapping("/categories/{id}/delete")
    public String deleteCategory(@PathVariable int id)
    {
        categoryDao.deleteCategory(id);

        return "redirect:/categories";
    }
}
