package com.mindhub.homeBanking.controllers;

import com.mindhub.homeBanking.DTO.TransferDTO;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.AccountRepository;
import com.mindhub.homeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    @PostMapping("/clients/current/transactions")
    public ResponseEntity<Object> createTransaction(Authentication authentication, @RequestBody TransferDTO transferDTO){

        Client client = clientRepository.findByEmail(authentication.getName());
        Account sourceAccount = accountRepository.findByNumber(transferDTO.getSourceAccount());
        Account destinationAccount = accountRepository.findByNumber(transferDTO.getDestinationAccount());
        Set<Account> accounts = client.getAccounts();

        if (transferDTO.getDescription().isBlank()){
            return new ResponseEntity<>("Please complete with a description", HttpStatus.FORBIDDEN);
        }
        if (transferDTO.getSourceAccount().isBlank()){
            return new ResponseEntity<>("Please set the source account", HttpStatus.FORBIDDEN);
        }
        if (transferDTO.getDestinationAccount().isBlank()){
            return new ResponseEntity<>("Please set the destination account", HttpStatus.FORBIDDEN);
        }
        if (transferDTO.getAmount() == 0){
            return new ResponseEntity<>("Please set a valid amount", HttpStatus.FORBIDDEN);
        }
        if (transferDTO.getSourceAccount() == transferDTO.getDestinationAccount()){
            return new ResponseEntity<>("You can't transfer to the same account", HttpStatus.FORBIDDEN);
        }

    }



}
