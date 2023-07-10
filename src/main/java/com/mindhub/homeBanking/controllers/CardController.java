package com.mindhub.homeBanking.controllers;

import com.mindhub.homeBanking.enums.CardColor;
import com.mindhub.homeBanking.enums.CardType;
import com.mindhub.homeBanking.models.Card;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.CardRepository;
import com.mindhub.homeBanking.repositories.ClientRepository;
import com.mindhub.homeBanking.services.CardService;
import com.mindhub.homeBanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCards(@RequestParam CardType cardType, @RequestParam CardColor cardColor, Authentication authentication){
        Client client = clientService.findByEmail(authentication.getName());
        String randomCardNumber;
        Random randomNumber = new Random();
        //Para los 16 digitos de la tarjeta
        int randomNumber1 = randomNumber.nextInt(9000) + 999;
        int randomNumber2 = randomNumber.nextInt(9000) + 999;
        int randomNumber3 = randomNumber.nextInt(9000) + 999;
        int randomNumber4 = randomNumber.nextInt(9000) + 999;
        //Para el CVV
        int randomCvvNumber = randomNumber.nextInt(900) + 100;

        do {
            randomCardNumber = randomNumber1 + "-" + randomNumber2 + "-" + randomNumber3 + "-" + randomNumber4;
        } while (cardService.findByNumber(randomCardNumber) != null );

        if(client.getCards().stream().filter(card -> card.getType() == cardType).count() >= 3) {
            return new ResponseEntity<>("Max amount of cards reached", HttpStatus.FORBIDDEN);

        } if (client.getCards().stream().anyMatch(card -> card.getType() == cardType && card.getColor() == cardColor)){
            return new ResponseEntity<>("Can't create another card with same color and type", HttpStatus.FORBIDDEN);

        } else {
            Card newCard = new Card(client.getFirstName() + " " + client.getLastName(), cardType, cardColor, randomCardNumber, randomCvvNumber, LocalDateTime.now(), LocalDateTime.now().plusYears(5));
            client.addCard(newCard);
            cardService.save(newCard);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
