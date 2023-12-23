package com.geevin.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class GetCardDTO {
    @NonNull
    private String cardNo;
    @NonNull
    private int pin;

    private double cardLimit;
}
