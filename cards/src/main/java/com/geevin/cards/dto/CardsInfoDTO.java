package com.geevin.cards.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class CardsInfoDTO {
    private double cardCashLimit;
    private double cardCashSpent;
    private Instant expirtyDate;
}
