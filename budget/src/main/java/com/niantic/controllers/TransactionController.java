package com.niantic.controllers;

import com.niantic.models.Transactions;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Controller
public class TransactionController
{
    private TransactionDao transactionDao = new TransactionDao();

    @GetMapping("/transactions")
    public String getAllTransactions(Model model)
    {
        ArrayList<Transactions> transactions;

        transactions = transactionDao.getTransactions();

        model.addAttribute("transactions", transactions);

        return "transactions/index";
    }

    @GetMapping("/transactions/reports/{id}")
    public String getTransactionById(Model model, @PathVariable int id)
    {
        var transaction = transactionDao.getTransactionById(id);
        model.addAttribute("transaction", transaction);

        return "transactions/details";
    }

    @GetMapping("/transactions/add")
    public String addTransaction(Model model)
    {
        model.addAttribute("transaction", new Transactions());
        model.addAttribute("action", "add");
        return "transactions/add_edit";
    }

    @PostMapping("/transactions/add")
    public String addTransaction(Model model, @ModelAttribute("transactions") Transactions transaction)
    {
        transactionDao.addTransaction(transaction);
        model.addAttribute("transactions", transaction);
        return "transactions/add_success";
    }

    @GetMapping("/transactions/reports")
    public String reportByCategory(Model model, @RequestParam(required = false, defaultValue = "0") int categoryId)
    {
        var transactions = transactionDao.getCategoryById(categoryId);
        model.addAttribute("transactions", transactions);

        return "transactions/report_by_category";
    }

}
