package com.mindhub.homeBanking.DTO;


import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO{

    private long id;
    private String number;
    private LocalDate date;
    private Double balance;
    private boolean active;
    private String type;
    private Client client;
    public Set<TransactionDTO> transactions;

    public AccountDTO(Account account) {
        number = account.getNumber();
        date = account.getDate();
        balance = account.getBalance();
        active = account.isActive();
        type = account.getType();
        transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        id = account.getId();
        client = account.getClient();
    }

    public Long getId(){return id;}
    public String getNumber(){ return number;}

    public LocalDate getDate(){ return date;}

    public Double getBalance(){ return balance; }

    public boolean isActive() {return active;}

    public String getType() {return type;}

    public Client getOwner(){return client;}

}
