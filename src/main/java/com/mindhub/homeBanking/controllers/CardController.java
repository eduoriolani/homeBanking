package com.mindhub.homeBanking.controllers;

import com.mindhub.homeBanking.DTO.CardPaymentDTO;
import com.mindhub.homeBanking.enums.CardColor;
import com.mindhub.homeBanking.enums.CardType;
import com.mindhub.homeBanking.enums.TransactionType;
import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Card;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.Transaction;
import com.mindhub.homeBanking.services.AccountService;
import com.mindhub.homeBanking.services.CardService;
import com.mindhub.homeBanking.services.ClientService;
import com.mindhub.homeBanking.services.TransactionService;
import com.mindhub.homeBanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCards(@RequestParam CardType cardType, @RequestParam CardColor cardColor, Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        Set<Card> cardsActive = client.getCards().stream().filter( card -> card.isActive() == true).collect(Collectors.toSet());
        //Para los 16 digitos de la tarjeta
        String randomCardNumber;
        //Para el CVV
        String randomCvvNumber = CardUtils.getCvvNumber();

        do {
            randomCardNumber = CardUtils.getCardNumber();
        } while (cardService.findByNumber(randomCardNumber) != null);

        if (cardsActive.stream().filter(card -> card.getType() == cardType).count() >= 3) {
            return new ResponseEntity<>("Max amount of cards reached", HttpStatus.FORBIDDEN);
        }
        if (cardsActive.stream().anyMatch(card -> card.getType() == cardType && card.getColor() == cardColor)) {
            return new ResponseEntity<>("Can't create another card with same color and type", HttpStatus.FORBIDDEN);
        } else {
            Card newCard = new Card(client.getFirstName() + " " + client.getLastName(), cardType, cardColor, randomCardNumber, randomCvvNumber,true , LocalDateTime.now(), LocalDateTime.now().plusYears(5));
            client.addCard(newCard);
            cardService.save(newCard);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PatchMapping("/clients/current/cards")
    public ResponseEntity<Object> deleteCard(@RequestParam Long id, Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        Set<Card> cards = client.getCards();
        Card card = null;
        for (Card c : cards) {
            if (c.getId().equals(id)) {
                card = c;
                break;
            }
        }

        if (!cards.contains(card)) {
            return new ResponseEntity<>("Card doesn't belong to the client", HttpStatus.BAD_REQUEST);
        }
        if (card == null) {
            return new ResponseEntity<>("Card doesn't exist", HttpStatus.NOT_FOUND);
        }
        card.setActive(false);
        cardService.save(card);
        return new ResponseEntity<>("Card deleted successfully", HttpStatus.OK);

    }

    @Transactional
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/clients/cardPayment")
    public ResponseEntity<Object> cardPayment(@RequestBody CardPaymentDTO cardPaymentDTO){
        Account account = accountService.findByNumber(cardPaymentDTO.getAccountNumber());
        Card card = cardService.findByNumber(cardPaymentDTO.getCardNumber());
        Client client = card.getClient();

        if (client == null){
            return new ResponseEntity<>("Client doesn't exist", HttpStatus.BAD_REQUEST);
        }
        if (account == null){
            return new ResponseEntity<>("Set an existing account", HttpStatus.BAD_REQUEST);
        }
        if (!card.getNumber().equals(cardPaymentDTO.getCardNumber())){
            return new ResponseEntity<>("Card number is invalid", HttpStatus.BAD_REQUEST);
        }
        if (!card.isActive()){
            return new ResponseEntity<>("Selected card has expired", HttpStatus.BAD_REQUEST);
        }
        if (cardPaymentDTO.getAmount() <= 0){
            return new ResponseEntity<>("Set a valid amount", HttpStatus.BAD_REQUEST);
        }
        if (account.getBalance() < cardPaymentDTO.getAmount()){
            return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
        } else {
            Transaction transaction = new Transaction(TransactionType.DEBIT, cardPaymentDTO.getAmount(), account.getBalance() - cardPaymentDTO.getAmount(), cardPaymentDTO.getDescription(), LocalDateTime.now());
            account.setBalance(account.getBalance() - cardPaymentDTO.getAmount());
            account.addTransactions(transaction);
            accountService.save(account);
            transactionService.save(transaction);
            return new ResponseEntity<>("Payment succeed", HttpStatus.OK);
        }
    }
}
