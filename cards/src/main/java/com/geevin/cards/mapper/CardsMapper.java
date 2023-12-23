package com.geevin.cards.mapper;

import com.geevin.cards.dto.CardsInfoDTO;
import com.geevin.cards.entity.CardsInfoEntity;

public class CardsMapper {

    public static CardsInfoDTO cardsInfoToDTO(CardsInfoDTO cdto, CardsInfoEntity cie) {
        cdto.setCardCashLimit(cie.getCardCashLimit());
        cdto.setCardCashSpent(cie.getCardCashSpent());
        cdto.setExpirtyDate(cie.getExpirtyDate());
        return cdto;
    }

}
