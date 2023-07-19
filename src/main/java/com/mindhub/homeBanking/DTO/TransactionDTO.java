package com.mindhub.homeBanking.DTO;

import com.mindhub.homeBanking.models.Transaction;
import com.mindhub.homeBanking.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public class TransactionDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private Double balance;
    private String description;
    private LocalDateTime date;


    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.balance = transaction.getBalance();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getBalance() {return balance;}

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
