package com.mindhub.homeBanking.dtos;

import com.mindhub.homeBanking.models.Account;

import java.time.LocalDateTime;
import java.util.Set;

public class AccountDTO {

    private long id;
    private String number;
    private LocalDateTime date;
    private Double balance;

    public AccountDTO(){}
    public AccountDTO(String number, LocalDateTime date, Double balance){
        this.number = number;
        this.date = date;
        this.balance = balance;
    }
    public String getNumber(){ return number;}
    public void setNumber(String number){ this.number = number;}

    public LocalDateTime getDate(){ return date;}
    public void setDate(LocalDateTime date){ this.date = date;}

    public Double getBalance(){ return this.balance = balance; }
    public void setBalance(LocalDateTime date){ this.date = date;}

    public String toString(){ return number + " " + date + " " + balance;}

}
