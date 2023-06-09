package com.mindhub.homeBanking.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homeBanking.DTO.AccountDTO;
import com.mindhub.homeBanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static java.util.stream.Collectors.toList;

@RestController @RequestMapping ("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/accounts")
    @JsonIgnore
    public List<AccountDTO> getAccounts(){return accountRepository
            .findAll()
            .stream()
            .map(AccountDTO::new)
            .collect(toList());}

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccounts(@PathVariable Long id)
    {return accountRepository.findById(id).map(AccountDTO::new).orElse(null);}

}
