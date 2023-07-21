package com.mindhub.homeBanking.DTO;

import java.util.List;

public class LoanCreateDTO {

    private String name;
    private Double maxAmount;
    private List<Integer> payments;
    private Integer taxPercentage;

    public LoanCreateDTO() {}

    public LoanCreateDTO(String name, Double maxAmount, List<Integer> payments, Integer taxPercentage) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
        this.taxPercentage = taxPercentage;
    }

    public String getName() {
        return name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }
    public Integer getTaxPercentage() {
        return taxPercentage;
    }
}

