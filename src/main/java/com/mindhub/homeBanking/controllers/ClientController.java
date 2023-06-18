package com.mindhub.homeBanking.controllers;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.models.Client;
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

}
