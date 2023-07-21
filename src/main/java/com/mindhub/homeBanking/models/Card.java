package com.mindhub.homeBanking.models;

import org.hibernate.annotations.GenericGenerator;
import com.mindhub.homeBanking.enums.CardType;
import com.mindhub.homeBanking.enums.CardColor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String cardHolder;
    private CardType type;
    private CardColor color;
    private String number;
    private String cvv;
    private boolean isActive;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Card (){}

    public Card(String cardHolder, CardType type, CardColor color, String number, String cvv,boolean isActive, LocalDateTime fromDate, LocalDateTime thruDate) {
        this.cardHolder = cardHolder;
        this.type = type;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.isActive= isActive;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    public Long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isActive() {return isActive;}

    public void setActive(boolean active) {isActive = active;}

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
