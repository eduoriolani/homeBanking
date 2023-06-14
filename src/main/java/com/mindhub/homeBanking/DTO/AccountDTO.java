package com.mindhub.homeBanking.DTO;


import com.mindhub.homeBanking.models.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO{

    private long id;
    private String number;
    private LocalDate date;
    private Double balance;
    public Set<TransactionDTO> transactions;

    public AccountDTO(){};
    public AccountDTO(Account account){
        this.number = account.getNumber();
        this.date = account.getDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        this.id = account.getId();
    }

    public Long getId(){return id;}
    public String getNumber(){ return number;}

    public LocalDate getDate(){ return date;}

    public Double getBalance(){ return this.balance = balance; }

}
