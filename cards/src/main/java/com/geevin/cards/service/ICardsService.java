package com.geevin.cards.service;

import com.geevin.cards.dto.CardsDTO;
import com.geevin.cards.dto.CardsInfoDTO;
import com.geevin.cards.entity.CardsInfoEntity;

public interface ICardsService {
    CardsInfoEntity createCard(CardsDTO cardsDTO);
    boolean updateCardPin(String cardNo, int oldPin, int newPin);

    boolean updateCardType(String cardNo, int pin, String cardType);

    boolean changeCardLimit(String cardNo, int pin, double limit);

    boolean updateCardSpend(String cardNo, int pin, double spend);

    boolean deleteCard(String cardNo, int pin);

    CardsInfoDTO getCardDetails(String cardNo, int pin);

}
