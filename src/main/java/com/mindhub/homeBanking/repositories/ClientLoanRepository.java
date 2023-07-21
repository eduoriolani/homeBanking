package com.mindhub.homeBanking.repositories;

import com.mindhub.homeBanking.DTO.ClientLoanDTO;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.ClientLoan;
import com.mindhub.homeBanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface ClientLoanRepository extends JpaRepository<ClientLoan, Long> {
    ClientLoan findByClientAndLoan(Client client, Loan loan);
}
