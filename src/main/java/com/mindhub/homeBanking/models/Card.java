package com.mindhub.homeBanking.models;

import com.mindhub.homeBanking.enums.CardColorType;
import com.mindhub.homeBanking.enums.CardType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private CardType cardType;
    private CardColorType cardColorType;
    private Long number;
    private LocalDate fromDate;
    private LocalDate thruDate;
    private Integer cvv;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    private String cardHolder;

    public Card(){}

    public Card(CardType cardType, CardColorType cardColorType, Long number, LocalDate fromDate, LocalDate thruDate, String cardHolder, Integer cvv) {
        this.cardType = cardType;
        this.cardColorType = cardColorType;
        this.number = number;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
    }

    public Client getClient() {
        return client;
    }

    public Long getId() {
        return id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardColorType getCardColorType() {
        return cardColorType;
    }

    public void setCardColorType(CardColorType cardColorType) {
        this.cardColorType = cardColorType;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
}
