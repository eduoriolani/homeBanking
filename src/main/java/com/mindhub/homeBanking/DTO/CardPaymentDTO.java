package com.mindhub.homeBanking.DTO;

public class CardPaymentDTO {

    private String cardNumber, description, accountNumber;
    private Double amount;
    private Short cvv;

    public CardPaymentDTO(String cardNumber, String description, String accountNumber, Double amount, Short cvv) {
        this.cardNumber = cardNumber;
        this.description = description;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public Short getCvv() {
        return cvv;
    }
}
