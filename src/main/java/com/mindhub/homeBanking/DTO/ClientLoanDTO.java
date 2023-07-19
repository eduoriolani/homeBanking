package com.mindhub.homeBanking.DTO;
import com.mindhub.homeBanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private String name;
    private Double amount;
    private Integer taxPercentage;
    private Integer payments;

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        name = clientLoan.getLoan().getName();
        amount = clientLoan.getAmount();
        taxPercentage = clientLoan.getTaxPercentage();
        payments = clientLoan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getTaxPercentage() {return taxPercentage;}

    public Integer getPayments() {
        return payments;
    }
}
