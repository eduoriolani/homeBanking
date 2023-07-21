package com.mindhub.homeBanking.services;

import com.mindhub.homeBanking.DTO.ClientDTO;
import com.mindhub.homeBanking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface ClientService {

    ClientDTO getClientDTO(Long id);
    Set<ClientDTO> getClientsDTO();
    Client findById(Long id);
    Client findByEmail(String email);
    ClientDTO getCurrentClient(Authentication authentication);
    Client save(Client client);
}
