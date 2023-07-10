package com.mindhub.homeBanking.services.implement;

import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.ClientRepository;
import com.mindhub.homeBanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public ClientDTO getClientDTO(Long id) {
        return new ClientDTO(clientRepository.findById(id).orElse(null));
    }

    @Override
    public Set<ClientDTO> getClientsDTO() {
        return clientRepository
                .findAll()
                .stream()
                .map(ClientDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public ClientDTO getCurrentClient(Authentication authentication) {return new ClientDTO(clientRepository.findByEmail(authentication.getName()));}

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
