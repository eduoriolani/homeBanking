package com.mindhub.homeBanking.repositories;

import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface ClientLoanRepository extends JpaRepository<ClientLoan, Long> {
    List<ClientLoan> findById(long id);
}
