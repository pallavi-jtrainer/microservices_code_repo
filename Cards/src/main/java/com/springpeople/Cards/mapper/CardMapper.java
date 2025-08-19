package com.springpeople.Cards.mapper;

import com.springpeople.Cards.dto.CardDto;
import com.springpeople.Cards.entity.Card;

public class CardMapper {
	
	public static CardDto mapToCardDto(Card card, CardDto cardDto) {
		cardDto.setMobileNumber(card.getMobileNumber());
		cardDto.setCardNumber(card.getCardNumber());
		cardDto.setCardType(card.getCardType());
		cardDto.setCardLimit(card.getCardLimit());
		cardDto.setAmountUsed(card.getAmountUsed());
		cardDto.setAmountAvailable(card.getAmountAvailable());
		return cardDto;
	}
	
	public static Card mapToCard(CardDto cardDto, Card card) {
		card.setMobileNumber(cardDto.getMobileNumber());
		card.setCardNumber(cardDto.getCardNumber());
		card.setCardType(cardDto.getCardType());
		card.setCardLimit(cardDto.getCardLimit());
		card.setAmountUsed(cardDto.getAmountUsed());
		card.setAmountAvailable(cardDto.getAmountAvailable());
		return card;
	}
}
