package com.springpeople.Cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card")
@Getter @Setter @AllArgsConstructor 
@NoArgsConstructor
@ToString
public class Card extends MappedClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	
	private String mobileNumber;
	
	private String cardNumber;
	
	private String cardType;
	
	private int cardLimit;
	
	private int amountUsed;
	
	private int amountAvailable;
}
