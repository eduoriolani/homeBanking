package com.mindhub.homeBanking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;


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

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private List<ClientLoan> clientLoan;

@JsonIgnore
    public List<ClientLoan> getClients(){return clientLoan;}


    public Loan (){}

    public Loan (String name, List<Integer> payments, Double maxAmount){
        this.name = name;
        this.payments = payments;
        this.maxAmount = maxAmount;
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

}
