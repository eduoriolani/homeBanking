package com.mindhub.homeBanking.repositories;

import com.mindhub.homeBanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Set<Transaction> findById(long id);

}
