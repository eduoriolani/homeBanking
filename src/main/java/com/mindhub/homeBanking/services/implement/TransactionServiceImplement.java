package com.mindhub.homeBanking.services.implement;

import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Transaction;
import com.mindhub.homeBanking.repositories.TransactionRepository;
import com.mindhub.homeBanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccountAndDateRange(Account account, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByAccountAndDateBetween(account, startDate, endDate);
    }
}
