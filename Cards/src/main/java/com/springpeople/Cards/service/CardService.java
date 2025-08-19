package com.springpeople.Cards.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springpeople.Cards.constants.CardConstants;
import com.springpeople.Cards.dto.CardDto;
import com.springpeople.Cards.dto.CustomerDto;
import com.springpeople.Cards.entity.Card;
import com.springpeople.Cards.exception.CardAlreadyExistsException;
import com.springpeople.Cards.exception.ResourceNotFoundException;
import com.springpeople.Cards.mapper.CardMapper;
import com.springpeople.Cards.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private APIClient apiClient;
	
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
	
//    public CardDto fetchCard(String mobileNumber) {
//        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
//                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
//        );
//        return CardMapper.mapToCardDto(card, new CardDto());
//    }
	
	public CardDto fetchCardDetails(String mobileNumber) {
//		ResponseEntity<CustomerDto> responseEntity = restTemplate
//				.getForEntity("http://localhost:8092/api/fetch?mobile=" + mobileNumber, CustomerDto.class);
//		CustomerDto customerDto = responseEntity.getBody();
		
		CustomerDto customerDto = apiClient.getCustomerDetails(mobileNumber);
		
		Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
              () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
		
		CardDto cardDto = new CardDto();
		CardMapper.mapToCardDto(card, cardDto);
		cardDto.setCustomerDetails(customerDto);
		
		return cardDto;
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
