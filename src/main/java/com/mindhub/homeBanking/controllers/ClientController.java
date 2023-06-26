package com.mindhub.homeBanking.controllers;

import java.util.Set;
import java.util.stream.Collectors;
import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.ClientRepository;
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
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping ("/clients")
    public Set<ClientDTO> getClient(){ return clientRepository
            .findAll()
            .stream()
            .map(ClientDTO::new)
            .collect(Collectors.toSet()); }

    @RequestMapping ("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        Client client = clientRepository.findById(id).orElse(null);
        ClientDTO clientDTO = new ClientDTO(client);
        return clientDTO;
    }
    @RequestMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication authentication){
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }


    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password){
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Please complete all the fields", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(email) != null){
            return new ResponseEntity<>("Email already in use, please try again", HttpStatus.FORBIDDEN);
        }
        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
