package com.mindhub.homeBanking.DTO;

import com.mindhub.homeBanking.models.Account;

public class LoanPaymentDTO {

    private String name;
    private Double amount;
    private Integer payment;
    private String account;

    public LoanPaymentDTO(String name, Double amount, Integer payment, String account) {
        this.name = name;
        this.amount = amount;
        this.payment = payment;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayment() {
        return payment;
    }

    public String getAccount() {
        return account;
    }
}
