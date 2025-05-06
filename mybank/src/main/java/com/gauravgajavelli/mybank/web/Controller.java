package com.gauravgajavelli.mybank.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauravgajavelli.mybank.model.TransactionDto;
import com.gauravgajavelli.mybank.service.TransactionService;
import com.gauravgajavelli.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class Controller {
    // find and create transactions
        // id, amount, timestamp, and reference
        // int int string string
        // timestamp must be formatted as yyyy-MM-dd’T’HH:mm’Z'
        // Use Java 8 datetimes
        // See how to read in command line arguments

    private TransactionService transactionService;
    private ObjectMapper objectMapper;

    @Value("bank.slogan")
    private String slogan;

    public Controller(TransactionService invoiceService) {
        this.transactionService = invoiceService;
    }

    @GetMapping("/transactions")
    public List<TransactionAndSlogan> getTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        List<TransactionAndSlogan> toWrite = new ArrayList<TransactionAndSlogan>();
        for (Transaction transaction : transactions) {
            toWrite.add(new TransactionAndSlogan(transaction, this.slogan));
        }
        return toWrite;
    }

    @PostMapping("/transactions")
    public TransactionAndSlogan addTransaction(@RequestBody TransactionDto transaction) {
        Integer id = Integer.valueOf(transaction.getId());
        Integer amount = Integer.valueOf(transaction.getAmount());
        String timestamp = String.valueOf(transaction.getTimestampTime());
        String reference = transaction.getReference();

        Transaction toRet = transactionService.create(id, amount, timestamp, reference);
        return new TransactionAndSlogan(toRet, timestamp);
    }

    private class TransactionAndSlogan extends Transaction {
        public String slogan;
        public TransactionAndSlogan(Transaction transaction, String slogan) {
            super(transaction.id, transaction.amount, transaction.timestampTime, transaction.reference);
            this.slogan = slogan;
        }
    }
}
