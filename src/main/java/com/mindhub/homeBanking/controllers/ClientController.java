package com.mindhub.homeBanking.controllers;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping ("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping ("/clients")
    @JsonIgnore
    public List<ClientDTO> getClient(){ return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList()); }

    @RequestMapping ("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){ return clientRepository.findById(id).map(ClientDTO::new).orElse(null); }

}
