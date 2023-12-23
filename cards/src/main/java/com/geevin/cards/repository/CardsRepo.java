package com.geevin.cards.repository;


import com.geevin.cards.entity.CardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepo extends JpaRepository<CardsEntity, String> {

}
