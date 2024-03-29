package com.mindhub.homeBanking.DTO;

import com.mindhub.homeBanking.models.Client;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public Set<AccountDTO> accounts;
    public Set<ClientLoanDTO> loans;
    public Set<CardDTO> cards;

    public ClientDTO(Client client) {

        id = client.getId();

        firstName = client.getFirstName();

        lastName = client.getLastName();

        email = client.getEmail();

        password = client.getPassword();

        accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());

        loans = client.getLoans()
                .stream()
                .map(ClientLoanDTO::new)
                .collect(Collectors.toSet());

        cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());

        }

    public long getId(){ return id;}
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public String getPassword(){return password;}
    public String getEmail(){
        return email;
    }
    public Set<AccountDTO> getAccounts(){return accounts;}
    public Set<ClientLoanDTO> getLoans(){return loans;}
    public Set<CardDTO> getCards() {return cards;}



}

