package com.mindhub.homeBanking.controllers;

import com.mindhub.homeBanking.DTO.AccountDTO;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Card;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.services.AccountService;
import com.mindhub.homeBanking.services.ClientService;
import com.mindhub.homeBanking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController @RequestMapping ("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public Set<AccountDTO> getAccountsDTO(){return accountService.getAccountsDTO();}

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountDTO(@PathVariable Long id)
    {return accountService.getAccountDTO(id);}


    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication, @RequestParam String type){
        Client client = clientService.findByEmail(authentication.getName());
        Set<Account> accounts = client.getAccounts().stream().filter(account -> account.isActive() == true).collect(Collectors.toSet());
        String randomNum;

        do {
            randomNum = AccountUtils.getRandomNum();
        } while (accountService.findByNumber(randomNum) != null);

        if ( accounts.size() >= 3 ){
            return new ResponseEntity<>("Max amount of accounts reached", HttpStatus.FORBIDDEN);
        } else {
            Account newAccount = new Account(randomNum, LocalDate.now(), 0.0,true , type);
            client.addAccounts(newAccount);
            accountService.save(newAccount);
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        }
    }

    @PatchMapping("clients/current/accounts")
    public ResponseEntity<Object> deleteAccount(Authentication authentication, @RequestParam Long id){
        Client client = clientService.findByEmail(authentication.getName());
        Set<Account> accounts = client.getAccounts().stream().filter( account -> account.isActive() == true).collect(Collectors.toSet());
        Account account = null;
        for (Account a : accounts) {
            if (a.getId().equals(id)) {
                account = a;
                break;
            }
        }
        if(!accounts.contains(account)){
            return new ResponseEntity<>("Account doesn't belong to the client", HttpStatus.FORBIDDEN);
        }
        if(account == null){
            return new ResponseEntity<>("The account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if(account.getBalance() > 0){
            return new ResponseEntity<>("Balance must be 0", HttpStatus.FORBIDDEN);
        }else {
            account.setActive(false);
            accountService.save(account);
            return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
        }
    }



}
