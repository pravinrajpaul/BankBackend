package com.geevin.cards.dto;

import com.geevin.cards.types.CardType;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class CardsDTO {

    private Long customerId;
    private String cardType;

}
