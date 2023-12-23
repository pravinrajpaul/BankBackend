package com.geevin.cards.repository;

import com.geevin.cards.entity.CardsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsInfoRepo extends JpaRepository<CardsInfoEntity, String> {

}
