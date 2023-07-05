package com.mindhub.homeBanking.DTO;

public class LoanApplicationDTO {

private Long id;
private Double amount;
private Integer payment;
private String destinationAccount;
private String loanName;

public LoanApplicationDTO(){}

    public LoanApplicationDTO(Double amount,String loanName, Integer payment, String destinationAccount){
    this.amount = amount;
    this.payment = payment;
    this.destinationAccount = destinationAccount;
    this.loanName = loanName;

    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayment() {
        return payment;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public String getLoanName() {
        return loanName;
    }
}
