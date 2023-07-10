package com.mindhub.homeBanking.controllers;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.AccountRepository;
import com.mindhub.homeBanking.repositories.ClientRepository;
import com.mindhub.homeBanking.services.AccountService;
import com.mindhub.homeBanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping ("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountService accountService;

    @RequestMapping ("/clients")
    public Set<ClientDTO> getClientsDTO(){ return clientService.getClientsDTO(); }

    @RequestMapping ("/clients/{id}")
    public ClientDTO getClientDTO(@PathVariable Long id){
        return clientService.getClientDTO(id);
    }
    @RequestMapping("/clients/current")
    public ResponseEntity<Object> getCurrentClient(Authentication authentication){
        ClientDTO clientDTO = clientService.getCurrentClient(authentication);
        return new ResponseEntity<>(clientDTO, HttpStatus.ACCEPTED);
    }



    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password){
        if (firstName.isBlank()) {
            return new ResponseEntity<>("Please complete your first name", HttpStatus.FORBIDDEN);
        }
        if (lastName.isBlank()) {
            return new ResponseEntity<>("Please complete your last name", HttpStatus.FORBIDDEN);
        }
        if (email.isBlank()) {
            return new ResponseEntity<>("Please complete your email", HttpStatus.FORBIDDEN);
        }
        if (password.isBlank()) {
            return new ResponseEntity<>("Please complete your password", HttpStatus.FORBIDDEN);
        }
        if (clientService.findByEmail(email) != null){
            return new ResponseEntity<>("Email already in use, please try again", HttpStatus.FORBIDDEN);
        } else {
            Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
            clientService.save(client);
            String randomNum;
            do {
                Random random = new Random();
                randomNum = "VIN-" + random.nextInt(90000000);
            } while (accountService.findByNumber(randomNum) != null);
            Account newAccount = new Account(randomNum, LocalDate.now(), 0.0);
            client.addAccounts(newAccount);
            accountService.save(newAccount);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


}
