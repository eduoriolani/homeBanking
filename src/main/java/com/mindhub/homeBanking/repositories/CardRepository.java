package com.mindhub.homeBanking.repositories;

import com.mindhub.homeBanking.enums.CardType;
import com.mindhub.homeBanking.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByNumber(String number);
}
