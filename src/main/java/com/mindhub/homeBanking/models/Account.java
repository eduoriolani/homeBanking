package com.mindhub.homeBanking.models;

import com.mindhub.homeBanking.dtos.AccountDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Account extends AccountDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String number;
    private LocalDateTime date;
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "owner_id")
    private Client owner;

    public Account (){}
    public Account (String number, LocalDateTime date, Double balance, Client client){
        this.number = number;
        this.date = date;
        this.balance = balance;
        this.owner = client;
    }

    public String getNumber(){ return number;}
    public void setNumber(String number){ this.number = number;}

    public LocalDateTime getDate(){ return date;}
    public void setDate(LocalDateTime date){ this.date = date;}

    public Double getBalance(){ return this.balance = balance; }
    public void setBalance(LocalDateTime date){ this.date = date;}

    public Client getOwner(){ return owner;}
    public void setOwner(Client owner){ this.owner = owner;}

    public String toString(){ return number + " " + date + " " + balance + " " + owner;}

}
