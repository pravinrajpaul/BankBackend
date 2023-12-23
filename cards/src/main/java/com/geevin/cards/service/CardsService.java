package com.geevin.cards.service;

import com.geevin.cards.dto.CardsDTO;
import com.geevin.cards.dto.CardsInfoDTO;
import com.geevin.cards.entity.CardsEntity;
import com.geevin.cards.entity.CardsInfoEntity;
import com.geevin.cards.exception.CardNotFoundException;
import com.geevin.cards.exception.DataBaseException;
import com.geevin.cards.exception.PinMisMatchException;
import com.geevin.cards.exception.UnknownCardTypeException;
import com.geevin.cards.mapper.CardsMapper;
import com.geevin.cards.repository.CardsInfoRepo;
import com.geevin.cards.repository.CardsRepo;
import com.geevin.cards.types.CardType;
import com.geevin.cards.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsService implements ICardsService{

    CardsInfoRepo cardsInfoRepo;
    CardsInfoEntity cardsInfoEntity;
    CardsRepo cardsRepo;
    CardsEntity cardsEntity;

    @Override
    public CardsInfoEntity createCard(CardsDTO cardsDTO) {
        cardsEntity.setCustomerId(cardsDTO.getCustomerId());
        String ict = cardsDTO.getCardType();
        CardType cardType = CardType.elementOfValue(ict);
        if (Util.isNull(cardType)) throw new UnknownCardTypeException("Unknown card type " + ict);
        cardsEntity.setCardType(cardType.toString());
        CardsEntity savedCardsEntity = null;
        try {
            savedCardsEntity = cardsRepo.save(cardsEntity);
        } catch (Exception e) {
            throw new DataBaseException("Unable to save to database. Try after some time. "+e.getMessage());
        }
        cardsInfoEntity.setCardNo(savedCardsEntity.getCardNo());
        cardsInfoEntity.setCardCashLimit(cardType.limit);
        cardsInfoEntity.setCardCashSpent(0);
        cardsInfoEntity.setExpirtyDate(Instant.now().plusSeconds(31536000*5));
        cardsInfoEntity.setCvv(new Random().nextInt(100, 1000));
        cardsInfoEntity.setPin(new Random().nextInt(1000, 10000));
        CardsInfoEntity savedCrardsInfoEntity;
        try {
            savedCrardsInfoEntity = cardsInfoRepo.save(cardsInfoEntity);
        } catch (Exception e) {
            throw new DataBaseException("Unable to save to database. Try after some time.");
        }
        return savedCrardsInfoEntity;
    }

    @Override
    public boolean updateCardPin(String cardNo, int oldPin, int newPin) {
        CardsInfoEntity cie = null;
        try {
            cie = cardsInfoRepo.findById(cardNo).orElseThrow(()-> new CardNotFoundException("Card "+cardNo+" not found."));
        } catch (Exception e) {
            throw new DataBaseException("Database error. Try after some time.");
        }
        if (cie.getPin() == oldPin) {
            cie.setPin(newPin);
            cardsInfoRepo.save(cie);
        }
        else throw new PinMisMatchException("Pin doesn't match with the original pin.");
        return true;
    }

    @Override
    public boolean updateCardType(String cardNo, int pin, String cardType) {
        CardsInfoEntity cie = null;
        CardsEntity ce = null;
        try {
            cie = cardsInfoRepo.findById(cardNo).orElseThrow(()-> new CardNotFoundException("Card "+cardNo+" not found."));
        } catch (Exception e) {
            throw new DataBaseException("Database error. Try after some time.");
        }
        if (cie.getPin() == pin) {
            try {
                ce = cardsRepo.findById(cie.getCardNo()).orElseThrow(()-> new CardNotFoundException("Card "+cardNo+" not found."));
                CardType ct =  CardType.elementOfValue(cardType);
                if (ct == null) throw new UnknownCardTypeException("Card type "+cardType+" unknown.");
                ce.setCardType(ct.toString());
                cie.setCardCashLimit(ct.limit);
            } catch (Exception e) {
                throw new DataBaseException("Database error. Try after some time.");
            }
            cardsRepo.save(ce);
            cardsInfoRepo.save(cie);
        }
        else throw new PinMisMatchException("Pin doesn't match with the original pin.");
        return true;
    }

    @Override
    public boolean changeCardLimit(String cardNo, int pin, double limit) {
        CardsInfoEntity cie = null;
        try {
            cie = cardsInfoRepo.findById(cardNo).orElseThrow(()-> new CardNotFoundException("Card "+cardNo+" not found."));
        } catch (Exception e) {
            throw new DataBaseException("Database error. Try after some time.");
        }
        if (cie.getPin() == pin) {
            cie.setCardCashLimit(limit);
            cardsInfoRepo.save(cie);
        }
        else throw new PinMisMatchException("Pin doesn't match with the original pin.");
        return true;
    }

    @Override
    public boolean updateCardSpend(String cardNo, int pin, double spend) {
        CardsInfoEntity cie = null;
        try {
            cie = cardsInfoRepo.findById(cardNo).orElseThrow(()-> new CardNotFoundException("Card "+cardNo+" not found."));
        } catch (Exception e) {
            throw new DataBaseException("Database error. Try after some time.");
        }
        if (cie.getPin() == pin) {
            cie.setCardCashSpent(spend);
            cardsInfoRepo.save(cie);
        }
        else throw new PinMisMatchException("Pin doesn't match with the original pin.");
        return true;
    }

    @Override
    public boolean deleteCard(String cardNo, int pin) {
        CardsInfoEntity cie = null;
        CardsEntity ce = null;
        try {
            cie = cardsInfoRepo.findById(cardNo).orElseThrow(()-> new CardNotFoundException("Card "+cardNo+" not found."));
        } catch (Exception e) {
            throw new DataBaseException("Database error. Try after some time.");
        }
        if (cie.getPin() == pin) {
            try {
                ce = cardsRepo.findById(cie.getCardNo()).orElseThrow(()-> new CardNotFoundException("Card "+cardNo+" not found."));
            } catch (Exception e) {
                throw new DataBaseException("Database error. Try after some time.");
            }
            cardsRepo.save(ce);
            cardsInfoRepo.save(cie);
        }
        else throw new PinMisMatchException("Pin doesn't match with the original pin.");
        return true;
    }

    @Override
    public CardsInfoDTO getCardDetails(String cardNo, int pin) {
        CardsInfoEntity cie = null;
        CardsEntity ce = null;
        try {
            cie = cardsInfoRepo.findById(cardNo).orElseThrow(()-> new CardNotFoundException(""));
        }
        catch (CardNotFoundException e) {
            throw new CardNotFoundException("Card "+cardNo+" not found.");
        }
        catch (Exception e) {
            throw new DataBaseException("Database error. Try after some time.");
        }
        if (cie.getPin() != pin) throw new PinMisMatchException("Pin doesn't match with the original pin.");
        return CardsMapper.cardsInfoToDTO(new CardsInfoDTO(), cie);
    }

}