package com.mindhub.homeBanking.controllers;

import com.mindhub.homeBanking.DTO.TransferDTO;
import com.mindhub.homeBanking.enums.TransactionType;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.Transaction;
import com.mindhub.homeBanking.services.AccountService;
import com.mindhub.homeBanking.services.ClientService;
import com.mindhub.homeBanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionService transactionService;

    @Transactional
    @PostMapping("/clients/current/transactions")
    public ResponseEntity<Object> createTransaction(Authentication authentication, @RequestBody TransferDTO transferDTO){

        if (transferDTO.getSourceAccount().isBlank()){
            return new ResponseEntity<>("Please set the source account", HttpStatus.FORBIDDEN);
        }
        if (transferDTO.getDestinationAccount().isBlank()){
            return new ResponseEntity<>("Please set the destination account", HttpStatus.FORBIDDEN);
        }


        Client client = clientService.findByEmail(authentication.getName());
        Account sourceAccount = accountService.findByNumber(transferDTO.getSourceAccount());
        Account destinationAccount = accountService.findByNumber(transferDTO.getDestinationAccount());
        Set<Account> accounts = client.getAccounts();

        if (transferDTO.getDescription().isBlank()){
            return new ResponseEntity<>("Please complete with a description", HttpStatus.FORBIDDEN);
        }
        if (transferDTO.getAmount() <= 0) {
            return new ResponseEntity<>("Please set a valid amount", HttpStatus.FORBIDDEN);
        }
        if (sourceAccount == null){
            return new ResponseEntity<>("Source account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (destinationAccount == null){
            return new ResponseEntity<>("Destination account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (sourceAccount.getNumber().equals(destinationAccount.getNumber())){
            return new ResponseEntity<>("You can't transfer to the same account", HttpStatus.FORBIDDEN);
        }

        if (!accounts.stream().anyMatch(account -> account.getNumber() == sourceAccount.getNumber())){
            return new ResponseEntity<>("The source account doesn't belong to the client", HttpStatus.FORBIDDEN);
        }
        if (sourceAccount.getBalance() < transferDTO.getAmount()){
            return new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);
        }
        else{
            Transaction debitTransfer = new Transaction(TransactionType.DEBIT, -transferDTO.getAmount(),  transferDTO.getDescription() + " " + destinationAccount.getNumber(), LocalDateTime.now() );
            Transaction creditTransfer = new Transaction(TransactionType.CREDIT, +transferDTO.getAmount(), transferDTO.getDescription() + " " + sourceAccount.getNumber(), LocalDateTime.now() );
            sourceAccount.addTransactions(debitTransfer);
            destinationAccount.addTransactions(creditTransfer);

            sourceAccount.setBalance(sourceAccount.getBalance() - transferDTO.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transferDTO.getAmount());
            accountService.save(sourceAccount);
            accountService.save(destinationAccount);
            transactionService.save(debitTransfer);
            transactionService.save(creditTransfer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }



}
