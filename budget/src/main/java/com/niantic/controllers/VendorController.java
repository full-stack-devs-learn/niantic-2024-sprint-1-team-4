package com.niantic.controllers;

import com.niantic.models.Transactions;
import com.niantic.models.Vendors;
import com.niantic.services.VendorDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class VendorController
{
    private VendorDao vendorDao = new VendorDao();

    @GetMapping("/vendors")
    public String getAllVendors(Model model)
    {
        ArrayList<Vendors> vendors;
        vendors = vendorDao.getVendors();
        model.addAttribute("vendors", vendors);
        return "vendors/index";
    }

    @GetMapping("/vendors/add")
    public String addVendor(Model model)
    {
        model.addAttribute("vendors", new Vendors());
        model.addAttribute("action", "add");
        return "vendors/add_edit";
    }

    @PostMapping("/vendors/add")
    public String addVendor(Model model, @ModelAttribute("vendors") Vendors vendor)
    {
        vendorDao.addVendor(vendor);
        model.addAttribute("vendors", vendor);
        return "vendors/add_success";
    }
}