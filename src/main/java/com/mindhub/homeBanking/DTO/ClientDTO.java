package com.mindhub.homeBanking.DTO;

import com.mindhub.homeBanking.models.Client;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    public Set<AccountDTO> accounts;

    public ClientDTO(){}

    public ClientDTO(Client client) {

        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());

        }


    public Set<AccountDTO> getAccounts(){return accounts;}

    public long getId(){ return id;}
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

}

