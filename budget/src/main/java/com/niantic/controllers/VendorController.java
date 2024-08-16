package com.niantic.controllers;

import com.niantic.models.Categories;
import com.niantic.models.Vendors;
import com.niantic.services.CategoryDao;
import com.niantic.services.VendorDao;
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
        vendors = vendorDao.getVendors();
        model.addAttribute("vendors", vendors);
        return "vendors/index";
    }

    @GetMapping("/vendors/add")
    public String addVendors(Model model)
    {
        model.addAttribute("vendors", new Vendors());
        model.addAttribute("action", "add");
        return "vendors/add_edit";
    }

}