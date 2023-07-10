package com.mindhub.homeBanking.services;

import com.mindhub.homeBanking.models.Card;
import org.springframework.context.annotation.Configuration;


public interface CardService {
    Card findByNumber(String number);
    void save(Card card);
}
