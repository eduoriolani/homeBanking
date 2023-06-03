package com.mindhub.homeBanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity


public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")


    private long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();
    public Set<Account> getAccounts(){ return accounts;}
    public void addAccounts (Account account){
        account.setOwner( this );
        accounts.add( account );
    }

    public Client(){}
    public Client(String first, String last, String contact){
        firstName = first;
        lastName = last;
        email = contact;
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
