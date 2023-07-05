package com.mindhub.homeBanking.controllers;

import com.mindhub.homeBanking.DTO.LoanApplicationDTO;
import com.mindhub.homeBanking.enums.TransactionType;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.ClientLoan;
import com.mindhub.homeBanking.models.Loan;
import com.mindhub.homeBanking.models.Transaction;
import com.mindhub.homeBanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createClientLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){

        Client client = clientRepository.findByEmail(authentication.getName());
        Account destinationAccount = accountRepository.findByNumber(loanApplicationDTO.getDestinationAccount());
        Loan loan = loanRepository.findByName(loanApplicationDTO.getLoanName());
        if(loan == null){
            return new ResponseEntity<>("Loan doesn't exist", HttpStatus.FORBIDDEN);
        }
        if(loanApplicationDTO.getAmount() <= 0){
            return new ResponseEntity<>("Please set a valid amount", HttpStatus.FORBIDDEN);
        }
        if(!loan.getPayments().contains(loanApplicationDTO.getPayment())){
            return new ResponseEntity<>("Please set a valid payment", HttpStatus.FORBIDDEN);
        }
        if(loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("Amount is higher than the max amount available", HttpStatus.FORBIDDEN);
        }
        if(destinationAccount == null){
            return new ResponseEntity<>("Destination account doesn't exist", HttpStatus.FORBIDDEN);
        } else {
            ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getPayment(), loanApplicationDTO.getAmount() + loanApplicationDTO.getAmount() * 0.2);
            Transaction loanTransaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loanApplicationDTO.getDestinationAccount() + " " + "loan approved", LocalDateTime.now());
            client.addClientLoan(clientLoan);
            loan.addClientLoan(clientLoan);
            destinationAccount.addTransactions(loanTransaction);
            destinationAccount.setBalance(destinationAccount.getBalance() + loanApplicationDTO.getAmount());
            clientLoanRepository.save(clientLoan);
            transactionRepository.save(loanTransaction);
            accountRepository.save(destinationAccount);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

}
