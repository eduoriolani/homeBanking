package com.mindhub.homeBanking.dtos;

import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;

import java.util.HashSet;
import java.util.Set;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public ClientDTO(Client client) {

        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

    }
    Set<AccountDTO> accounts = new HashSet<>();
    public Set<AccountDTO> getAccounts(){ return accounts;}
    public void addAccounts (Account account, Client client){
        account.setOwner( client );
        accounts.add( account );
    }
    public long getId(){ return id;}
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}

