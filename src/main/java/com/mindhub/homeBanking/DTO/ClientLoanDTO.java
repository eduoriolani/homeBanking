package com.mindhub.homeBanking.DTO;
import com.mindhub.homeBanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private String name;
    private Double amount;
    private Double totalAmount;
    private Integer taxPercentage;
    private Integer payments;
    private Integer selectedPayment;

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        name = clientLoan.getLoan().getName();
        amount = clientLoan.getAmount();
        totalAmount = clientLoan.getTotalAmount();
        taxPercentage = clientLoan.getTaxPercentage();
        payments = clientLoan.getPayments();
        selectedPayment = clientLoan.getSelectedPayment();
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

    public Double getTotalAmount() {return totalAmount;}

    public Integer getTaxPercentage() {return taxPercentage;}

    public Integer getPayments() {
        return payments;
    }

    public Integer getSelectedPayment() {return selectedPayment;}
}
