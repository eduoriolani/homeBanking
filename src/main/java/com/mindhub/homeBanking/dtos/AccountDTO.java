package com.mindhub.homeBanking.dtos;


import com.mindhub.homeBanking.models.Account;

import java.time.LocalDate;

public class AccountDTO extends Account {

    private long id;
    private String number;
    private LocalDate date;
    private Double balance;

    private ClientDTO owner;
    public AccountDTO(){}
    public AccountDTO(Account account, ClientDTO client){
        this.number = account.getNumber();
        this.date = account.getDate();
        this.balance = account.getBalance();
        this.owner = client;
    }
    public String getNumber(){ return number;}
    public void setNumber(String number){ this.number = number;}

    public LocalDate getDate(){ return date;}
    public void setDate(LocalDate date){ this.date = date;}

    public Double getBalance(){ return this.balance = balance; }
    public void setBalance(LocalDate date){ this.date = date;}

    public ClientDTO getOwner(){ return owner;}
    public void setOwner(ClientDTO owner){ this.owner = owner;}




}
