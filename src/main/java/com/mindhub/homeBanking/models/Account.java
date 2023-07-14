package com.mindhub.homeBanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String number;
    private LocalDate date;
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "client_id")
    private Client client;


    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    Set<Transaction> transactions = new HashSet<>();

    public Account (){}
    public Account (String number, LocalDate date, Double balance){
        this.number = number;
        this.date = date;
        this.balance = balance;
    }

    public Long getId(){return id;}

    public String getNumber(){ return number;}
    public void setNumber(String number){ this.number = number;}

    public LocalDate getDate(){ return date;}
    public void setDate(LocalDate date){ this.date = date;}
    public Double getBalance(){ return this.balance; }
    public void setBalance(Double balance){ this.balance = balance;}
    @JsonIgnore
    public Client getClient(){ return client;}
    public void setClient(Client client){ this.client = client;}
    public Set<Transaction> getTransactions(){return transactions;}
    public void addTransactions(Transaction transaction){
        transaction.setAccount(this);
        transactions.add(transaction);
    }



    public String toString(){ return number + " " + date + " " + balance ;}

}
