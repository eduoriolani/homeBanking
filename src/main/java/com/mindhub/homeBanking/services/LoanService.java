package com.mindhub.homeBanking.services;

import com.mindhub.homeBanking.DTO.LoanDTO;
import com.mindhub.homeBanking.models.Loan;

import java.util.Set;

public interface LoanService {
    Loan findByName(String name);
    Set<LoanDTO> getLoansDTO();
    Loan save(Loan loan);
}
