package com.geevin.cards.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Entity
@Table(name = "cards_info")
@Component
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class CardsInfoEntity extends CreateDetailsEntity{

    @Id
    private String cardNo;
    private double cardCashLimit;
    private double cardCashSpent;
    private Instant expirtyDate;
    private int cvv;
    private int pin;
}
