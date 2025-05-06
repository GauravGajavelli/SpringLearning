package com.gauravgajavelli.mybank.model;

import java.time.LocalDateTime;

public class Transaction {
    // id, amount, timestamp, and reference
    // int int string string
    // timestamp must be formatted as yyyy-MM-dd’T’HH:mm’Z'
    public int id;
    public int amount;
    public LocalDateTime timestampTime;
    public String reference;
    public Transaction(int id, int amount, String timestamp, String reference) {
        this.id = id;
        this.amount = amount;
        System.out.println("Prinplup: "+id);
        System.out.println("Prinplup: "+amount);
        System.out.println("Prinplup: "+timestamp);
        System.out.println("Prinplup: "+reference);
        this.timestampTime = LocalDateTime.parse(timestamp); // 2007-12-03T10:15:30
        this.reference = reference;
    }
    public Transaction(int id, int amount, LocalDateTime timestampTime, String reference) {
        this.id = id;
        this.amount = amount;
        this.timestampTime = timestampTime;
        this.reference = reference;
    }
}
