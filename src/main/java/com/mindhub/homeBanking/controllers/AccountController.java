package com.mindhub.homeBanking.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homeBanking.DTO.AccountDTO;
import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.AccountRepository;
import com.mindhub.homeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController @RequestMapping ("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping("/accounts")
    @JsonIgnore
    public Set<AccountDTO> getAccounts(){return accountRepository
            .findAll()
            .stream()
            .map(AccountDTO::new)
            .collect(Collectors.toSet());}

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccounts(@PathVariable Long id)
    {return accountRepository.findById(id).map(AccountDTO::new).orElse(null);}


    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        Set<Account> accounts = client.getAccounts();
        String randomNum;

        do {
            Random random = new Random();
            randomNum = "VIN-" + random.nextInt(90000000);
        } while (accountRepository.findByNumber(randomNum) != null);

        if ( accounts.size() >= 3 ){
            return new ResponseEntity<>("Max amount of accounts reached", HttpStatus.FORBIDDEN);
        } else {
            Account newAccount = new Account(randomNum, LocalDate.now(), 0.0);
            client.addAccounts(newAccount);
            accountRepository.save(newAccount);
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        }
    }

}
