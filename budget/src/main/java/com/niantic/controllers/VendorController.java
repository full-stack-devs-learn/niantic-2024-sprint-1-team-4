package com.niantic.controllers;

import com.niantic.models.Categories;
import com.niantic.services.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class VendorController
{
    private VendorDao vendorDao = new VendorDao();

    @GetMapping("/vendor")
    public String getAllVendors(Model model)
    {
        ArrayList<Vendors> vendors;
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

}