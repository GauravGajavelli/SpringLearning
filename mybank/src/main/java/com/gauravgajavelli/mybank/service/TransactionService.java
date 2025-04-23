package com.gauravgajavelli.mybank.service;

import com.gauravgajavelli.mybank.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService() {
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(int id, int amount, String timestamp, String reference) {
        Transaction transaction = new Transaction(id, amount, timestamp, reference);
        transactions.add(transaction);
        return transaction;
    }
}
