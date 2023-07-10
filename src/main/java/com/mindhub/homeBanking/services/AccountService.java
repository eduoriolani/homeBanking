package com.mindhub.homeBanking.services;

import com.mindhub.homeBanking.DTO.AccountDTO;
import com.mindhub.homeBanking.models.Account;

import java.util.Set;

public interface AccountService {

    Set<AccountDTO> getAccountsDTO();
    AccountDTO getAccountDTO(Long id);
    Account findByNumber(String number);
    Account save(Account account);
}
