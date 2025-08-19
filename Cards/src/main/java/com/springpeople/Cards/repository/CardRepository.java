package com.springpeople.Cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springpeople.Cards.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

	Optional<Card> findByMobileNumber(String mobile);
	Optional<Card> findByCardNumber(String cardNumber);
}
