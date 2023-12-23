package com.geevin.cards.entity;

import com.geevin.cards.generator.CardNoGenerator;
import com.geevin.cards.types.CardType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "cards")
@Component
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CardsEntity extends CreateDetailsEntity{

    private Long customerId;
    @Id
    @GeneratedValue(generator = "cardNoGenerator")
    @GenericGenerator(name = "cardNoGenerator", type = CardNoGenerator.class)
    private String cardNo;
    private String cardType;
}
