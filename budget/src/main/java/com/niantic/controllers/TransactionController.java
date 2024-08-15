package com.niantic.controllers;

import com.niantic.models.Transactions;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/transactions/{id}")
    public String getTransactionById(Model model, @PathVariable int id)
    {
        var transaction = transactionDao.getTransactionById(id);
        model.addAttribute("transaction", transaction);

        return "transactions/details";
    }

}
