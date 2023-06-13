package com.mindhub.homeBanking.repositories;

import com.mindhub.homeBanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository <Account, Long> {
}

