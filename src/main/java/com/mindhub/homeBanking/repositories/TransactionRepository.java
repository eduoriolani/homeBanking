package com.mindhub.homeBanking.repositories;

import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountAndDateBetween(Account account, LocalDateTime startDate, LocalDateTime endDate);
}
