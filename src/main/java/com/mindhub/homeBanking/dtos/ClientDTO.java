package com.mindhub.homeBanking.dtos;

import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;

import java.util.HashSet;
import java.util.Set;

public class ClientDTO extends Client {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    Set<Account> accounts = new HashSet<>();

    public ClientDTO(){}

    public ClientDTO(Client client) {

        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.accounts = client.getAccounts();


        }




    public Set<Account> getAccounts(){ return accounts;}
    public void addAccounts (AccountDTO account){
        account.setOwner( this );
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

}

