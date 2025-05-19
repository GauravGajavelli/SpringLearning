package com.gauravgajavelli.mybank.web;

import com.gauravgajavelli.mybank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/account")
    public String login(@ModelAttribute @Valid TransactionForm transactionForm, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()) {
            return "account.html";
        }
        model.addAttribute("invalidCredentials", "true");
        return "login.html";
    }
}
