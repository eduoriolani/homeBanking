package com.mindhub.homeBanking.DTO;


import com.mindhub.homeBanking.models.Account;

import java.time.LocalDate;

public class AccountDTO{

    private long id;
    private String number;
    private LocalDate date;
    private Double balance;

    public AccountDTO(){};
    public AccountDTO(Account account){
        this.number = account.getNumber();
        this.date = account.getDate();
        this.balance = account.getBalance();

    }


    public String getNumber(){ return number;}

    public LocalDate getDate(){ return date;}

    public Double getBalance(){ return this.balance = balance; }

}
