package com.geevin.cards.controller;

import com.geevin.cards.dto.CardsDTO;
import com.geevin.cards.dto.CardsInfoDTO;
import com.geevin.cards.dto.GetCardDTO;
import com.geevin.cards.dto.ResponseDTO;
import com.geevin.cards.entity.CardsInfoEntity;
import com.geevin.cards.mapper.CardsMapper;
import com.geevin.cards.service.ICardsService;
import com.geevin.cards.types.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.time.Instant;

@RestController
@RequestMapping(path = "${application.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CardsController {

    ICardsService iCardsService;

    @GetMapping(path = "ping")
    public ResponseEntity<ResponseDTO> ping() {
        ResponseDTO response = new ResponseDTO(StatusCode.SUCCESS, "Ping successful", Instant.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "createCard", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createCard(@RequestBody CardsDTO cdto) {
        CardsInfoEntity cie = iCardsService.createCard(cdto);
        CardsInfoDTO cidto = CardsMapper.cardsInfoToDTO(new CardsInfoDTO(), cie);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(StatusCode.SUCCESS, cidto, Instant.now()));
    }

    @GetMapping(path = "getCardDetails")
    public ResponseEntity<ResponseDTO> getCardDetails(@RequestBody GetCardDTO cdto) {

        CardsInfoDTO cidto = iCardsService.getCardDetails(cdto.getCardNo(), cdto.getPin());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(StatusCode.SUCCESS, cidto, Instant.now()));
    }

    @PostMapping(path = "changeCardLimit")
    public ResponseEntity<ResponseDTO> changeCardLimit(@RequestBody GetCardDTO cdto) {

        double newCardLimit = cdto.getCardLimit();
        boolean success = iCardsService.changeCardLimit(cdto.getCardNo(), cdto.getPin(), newCardLimit);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(StatusCode.SUCCESS, "Successfully changed card limit to "+ newCardLimit, Instant.now()));
    }

}