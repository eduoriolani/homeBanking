package com.mindhub.homeBanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homeBanking.enums.TransactionType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator( name = "native", strategy = "native")
    private Long id;
    private TransactionType type;
    private Double amount;
    private Double balance;
    private String description;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(){}
    public Transaction(TransactionType type, Double amount, Double balance, String description, LocalDateTime date){
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.date = date;
    }

    public Long getId(){
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {return balance;}

    public void setBalance(Double balance) {this.balance = balance;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
