package com.mindhub.homeBanking.DTO;

import com.mindhub.homeBanking.models.Loan;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoanDTO {
    private Long id;
    private String name;
    private List<Integer> payments;
    private Double maxAmount;
    private Integer taxPercentage;
    private Set<ClientLoanDTO> clientLoans;

    public LoanDTO(Loan loan) {
        id = loan.getId();
        name = loan.getName();
        payments = loan.getPayments();
        maxAmount = loan.getMaxAmount();
        taxPercentage = loan.getTaxPercentage();
        clientLoans = loan.getClients().stream().map(ClientLoanDTO :: new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public Integer getTaxPercentage() {return taxPercentage;}

    public Set<ClientLoanDTO> getClientLoans() {
        return clientLoans;
    }
}
