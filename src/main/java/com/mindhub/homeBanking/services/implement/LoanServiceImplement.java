package com.mindhub.homeBanking.services.implement;

import com.mindhub.homeBanking.DTO.LoanDTO;
import com.mindhub.homeBanking.models.Loan;
import com.mindhub.homeBanking.repositories.LoanRepository;
import com.mindhub.homeBanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Override
    public Loan findByName(String name) {
        return loanRepository.findByName(name);
    }

    @Override
    public Set<LoanDTO> getLoansDTO() {
        return loanRepository.findAll().stream().map(LoanDTO :: new).collect(Collectors.toSet());
    }
}
