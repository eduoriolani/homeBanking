package com.mindhub.homeBanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "native")
    @GenericGenerator(name= "native", strategy= "native")
    private Long id;
    private String name;

    @ElementCollection
    @Column(name = "payment")
    private List<Integer> payments;
    private Double maxAmount;
    private Integer taxPercentage;

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();


    public Loan (){}

    public Loan (String name, List<Integer> payments, Double maxAmount, Integer taxPercentage){
        this.name = name;
        this.payments = payments;
        this.maxAmount = maxAmount;
        this.taxPercentage = taxPercentage;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getTaxPercentage() {return taxPercentage;}

    public void setTaxPercentage(Integer taxPercentage) {this.taxPercentage = taxPercentage;}

    @JsonIgnore
    public Set<ClientLoan> getClients(){return clientLoans;}
    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setLoan(this);
        clientLoans.add(clientLoan);
    }


}
