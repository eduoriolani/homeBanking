package com.mindhub.homeBanking.services;

import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public interface TransactionService {
    void save(Transaction transaction);
    List<Transaction> getTransactionsByAccountAndDateRange(Account account, LocalDateTime startDate, LocalDateTime endDate);
    }

