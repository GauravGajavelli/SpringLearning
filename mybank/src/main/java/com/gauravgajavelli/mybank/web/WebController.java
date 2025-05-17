package com.gauravgajavelli.mybank.web;

import com.gauravgajavelli.mybank.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController
{
    private TransactionService transactionService;

    public WebController(TransactionService invoiceService) {
        this.transactionService = invoiceService;
    }

    @GetMapping("/account/{userId}")
    public String getAccount(Model model, @PathVariable int userId) {
        System.out.println(userId);
        model.addAttribute("transactions", transactionService.getAccount(userId));
        return "account.html";
    }
}
