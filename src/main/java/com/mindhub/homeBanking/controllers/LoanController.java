package com.mindhub.homeBanking.controllers;

import com.mindhub.homeBanking.DTO.*;
import com.mindhub.homeBanking.enums.TransactionType;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.ClientLoan;
import com.mindhub.homeBanking.models.Loan;
import com.mindhub.homeBanking.models.Transaction;
import com.mindhub.homeBanking.repositories.*;
import com.mindhub.homeBanking.services.AccountService;
import com.mindhub.homeBanking.services.ClientService;
import com.mindhub.homeBanking.services.LoanService;
import com.mindhub.homeBanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @GetMapping("/loans")
    public Set<LoanDTO> getLoansDTO(){
        return loanService.getLoansDTO();
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createClientLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){

        Client client = clientService.findByEmail(authentication.getName());
        Account destinationAccount = accountService.findByNumber(loanApplicationDTO.getDestinationAccount());
        Loan loan = loanService.findByName(loanApplicationDTO.getLoanName());

        if(loan == null){
            return new ResponseEntity<>("Loan doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (client.getLoans().stream().anyMatch(clientLoan -> clientLoan.getLoan().getName().equals(loan.getName()))){
            return new ResponseEntity<>("Can't apply for two loans of the same type", HttpStatus.BAD_REQUEST);
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
            ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getPayment(), loanApplicationDTO.getPayment(), loan.getTaxPercentage(), loanApplicationDTO.getAmount(), loanApplicationDTO.getAmount());
            Transaction loanTransaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), destinationAccount.getBalance() + loanApplicationDTO.getAmount() , loanApplicationDTO.getDestinationAccount() + " " + "loan approved", LocalDateTime.now());
            client.addClientLoan(clientLoan);
            loan.addClientLoan(clientLoan);
            destinationAccount.addTransactions(loanTransaction);
            destinationAccount.setBalance(destinationAccount.getBalance() + loanApplicationDTO.getAmount());
            clientLoanRepository.save(clientLoan);
            transactionService.save(loanTransaction);
            accountService.save(destinationAccount);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }
    @Transactional
    @PostMapping("/loans/payment")
    public ResponseEntity<Object> payDues(Authentication authentication, @RequestBody LoanPaymentDTO loanPaymentDTO){
        Client client = clientService.findByEmail(authentication.getName());
        Account account = accountService.findByNumber(loanPaymentDTO.getAccount());
        Loan loan = loanService.findByName(loanPaymentDTO.getName());

        if ( client == null){
            return new ResponseEntity<>("Client doesn't exist", HttpStatus.FORBIDDEN);
        }
        if ( account == null){
            return new ResponseEntity<>("Please select a valid account", HttpStatus.BAD_REQUEST);
        }
        if ( loanPaymentDTO.getAmount() > account.getBalance()){
            return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
        }
        if (loan == null ){
            return new ResponseEntity<>("This loan hasn't been requested", HttpStatus.BAD_REQUEST);
        } else {
            ClientLoan clientLoan = clientLoanRepository.findByClientAndLoan(client, loan);
            clientLoan.setAmount(clientLoan.getAmount() - loanPaymentDTO.getAmount());
            clientLoan.setPayments(clientLoan.getPayments() - loanPaymentDTO.getPayment());
            Transaction transaction = new Transaction(TransactionType.DEBIT, loanPaymentDTO.getAmount(), account.getBalance() - loanPaymentDTO.getAmount(), "Due payment approved", LocalDateTime.now() );
            account.addTransactions(transaction);
            account.setBalance(account.getBalance() - loanPaymentDTO.getAmount());

            clientLoanRepository.save(clientLoan);
            accountService.save(account);
            transactionService.save(transaction);
            return new ResponseEntity<>("Payment of due has succeed", HttpStatus.OK);
        }
    }

    @PostMapping("/loans/create")
    public ResponseEntity<Object> createLoan(@RequestBody LoanCreateDTO loanCreateDTO){
        Loan loanCreated = loanService.findByName(loanCreateDTO.getName());
        if ( loanCreated != null && loanCreated.getName() != null){
            return new ResponseEntity<>("Can't create a new loan with same name", HttpStatus.FORBIDDEN);
        }
        if (loanCreateDTO.getMaxAmount() == 0){
            return new ResponseEntity<>("Please set a valid amount", HttpStatus.FORBIDDEN);
        }
        if (loanCreateDTO.getTaxPercentage() == 0){
            return new ResponseEntity<>("Please set a valid tax percentage", HttpStatus.FORBIDDEN);
        }
        if (loanCreateDTO.getPayments() == null){
            return new ResponseEntity<>("Please choose payments options", HttpStatus.FORBIDDEN);
        }else {
            Loan newLoan = new Loan (loanCreateDTO.getName(), loanCreateDTO.getPayments(), loanCreateDTO.getMaxAmount(), loanCreateDTO.getTaxPercentage());
            loanService.save(newLoan);
            return new ResponseEntity<>("Loan successfully created", HttpStatus.CREATED);
        }
    }

}
