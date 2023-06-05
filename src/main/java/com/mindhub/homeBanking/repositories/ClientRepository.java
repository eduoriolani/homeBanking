package com.mindhub.homeBanking.repositories;

import java.util.List;

import com.mindhub.homeBanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
    @RepositoryRestResource
public interface ClientRepository extends JpaRepository <Client, Long> {
        List <Client> findById(long id);
}
