package com.mindhub.homeBanking.DTO;

public class TransferDTO {

    private Double amount;
    private String description;
    private String sourceAccount;
    private String destinationAccount;

    public TransferDTO(){}

    public TransferDTO(Double amount, String description, String sourceAccount, String destinationAccount) {
        this.amount = amount;
        this.description = description;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }
}
