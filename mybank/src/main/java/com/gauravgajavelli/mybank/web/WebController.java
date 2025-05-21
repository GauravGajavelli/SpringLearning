package com.gauravgajavelli.mybank.web;

import com.gauravgajavelli.mybank.model.Transaction;
import com.gauravgajavelli.mybank.web.forms.TransactionDto;
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

    public WebController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/account/{userId}")
    public String getAccount(Model model, @PathVariable int userId) {
        System.out.println(userId);
        model.addAttribute("transactions", transactionService.getAccount(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("transactionForm", new TransactionDto());
        return "account.html";
    }

    @PostMapping("/account/{userId}")
    public String login(@ModelAttribute @Valid TransactionDto transaction, @PathVariable int userId, BindingResult bindingResult, Model model){
        Integer id = Integer.valueOf(transaction.getId());
        Integer amount = Integer.valueOf(transaction.getAmount());
        String timestamp = String.valueOf(transaction.getTimestamp());
        String reference = transaction.getReference();

        model.addAttribute("userId", userId);

        transactionService.create(id, amount, timestamp, reference);
        return "redirect:/account/"+userId;
    }
}
