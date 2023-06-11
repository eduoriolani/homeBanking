package com.mindhub.homeBanking.DTO;

import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long loan;
    private String name;
    private Client client;
    private Double amount;
    private Integer payments;

    public ClientLoanDTO(){}
    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loan = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public Long getLoan() {
        return loan;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }
}
