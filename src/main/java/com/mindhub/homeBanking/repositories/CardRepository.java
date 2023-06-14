package com.mindhub.homeBanking.repositories;

import com.mindhub.homeBanking.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
