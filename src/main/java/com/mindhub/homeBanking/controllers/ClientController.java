package com.mindhub.homeBanking.controllers;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homeBanking.dtos.ClientDTO;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ClientDTO> getClient(){ return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList()); }

}
