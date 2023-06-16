package com.mindhub.homeBanking.controllers;
import java.util.Set;
import java.util.stream.Collectors;

import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping ("/clients")
    public Set<ClientDTO> getClientsDTO(){ return clientRepository
            .findAll()
            .stream()
            .map(client -> new ClientDTO(client)).collect(Collectors.toSet()); }

    @RequestMapping ("/clients/{id}")
    public ClientDTO getClientDTO(@PathVariable Long id){
         Client client = clientRepository.findById(id).orElse(null);
         ClientDTO clientDTO = new ClientDTO(client);
         return clientDTO;
    }


}
