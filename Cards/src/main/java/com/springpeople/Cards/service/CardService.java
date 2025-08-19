package com.springpeople.Cards.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpeople.Cards.constants.CardConstants;
import com.springpeople.Cards.dto.CardDto;
import com.springpeople.Cards.entity.Card;
import com.springpeople.Cards.exception.CardAlreadyExistsException;
import com.springpeople.Cards.exception.ResourceNotFoundException;
import com.springpeople.Cards.mapper.CardMapper;
import com.springpeople.Cards.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
	public void createCard(String mobileNumber) {
		 Optional<Card> optionalCards= cardRepository.findByMobileNumber(mobileNumber);
	        if(optionalCards.isPresent()){
	            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
	        }
	        cardRepository.save(createNewCard(mobileNumber));
	}
	
	private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setCardLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAmountAvailable(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }
	
    public CardDto fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardDto(card, new CardDto());
    }

    
    public boolean updateCard(CardDto cardDto) {
        Card card = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardDto.getCardNumber()));
        CardMapper.mapToCard(cardDto, card);
        cardRepository.save(card);
        return  true;
    }

    public boolean deleteCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
    }
}
