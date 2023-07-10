package com.mindhub.homeBanking.services.implement;

import com.mindhub.homeBanking.models.Card;
import com.mindhub.homeBanking.repositories.CardRepository;
import com.mindhub.homeBanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    private CardRepository cardRepository;
    @Override
    public Card findByNumber(String number) {
       return cardRepository.findByNumber(number);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
