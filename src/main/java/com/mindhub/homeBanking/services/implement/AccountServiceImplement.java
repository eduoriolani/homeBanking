package com.mindhub.homeBanking.services.implement;

import com.mindhub.homeBanking.DTO.AccountDTO;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.repositories.AccountRepository;
import com.mindhub.homeBanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Set<AccountDTO> getAccountsDTO() {
        return accountRepository
                .findAll()
                .stream()
                .map(AccountDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public AccountDTO getAccountDTO(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        return new AccountDTO(account);
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
