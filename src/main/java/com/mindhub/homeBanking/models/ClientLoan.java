package com.mindhub.homeBanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator( name = "native", strategy = "native")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    private Integer payments;
    private Integer selectedPayment;
    private Integer taxPercentage;
    private Double amount;
    private Double totalAmount;

    public ClientLoan(){}

    public ClientLoan(Integer payments, Integer selectedPayment,Integer taxPercentage, Double amount, Double totalAmount) {
        this.payments = payments;
        this.selectedPayment = selectedPayment;
        this.taxPercentage = taxPercentage;
        this.amount = amount;
        this.totalAmount = totalAmount;

    }

    public Long getId() {
        return id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {this.payments = payments;}

    public Integer getSelectedPayment() {return selectedPayment;}

    public void setSelectedPayment(Integer selectedPayment) {this.selectedPayment = selectedPayment;}

    public Integer getTaxPercentage() {return taxPercentage;}

    public void setTaxPercentage(Integer taxPercentage) {this.taxPercentage = taxPercentage;}

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {return totalAmount;}

    public void setTotalAmount(Double totalAmount) {this.totalAmount = totalAmount;}
}
