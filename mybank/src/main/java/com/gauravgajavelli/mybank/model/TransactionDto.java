package com.gauravgajavelli.mybank.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Valid
public class TransactionDto {

    @Min(0)
    private int id;

    @Min(0)
    private int amount;

    @NotNull
    private String timestamp;

    @NotEmpty
    private String reference;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTimestampTime() {
        return timestamp;
    }

    public void setTimestampTime(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
