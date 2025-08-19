package com.springpeople.Cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
		name="Card",
		description = "Holds Card details"
		)
@Data
public class CardDto {

	@NotEmpty(message = "Mobile number cannot be empty or null")
	@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number has to be 10 digits only")
	@Schema(
			name="MobileNumber",
			description = "Mobile number of Customer", example="7377272722"
			)
	private String mobileNumber;
	
	@NotEmpty(message = "Card Number cannot be null or empty")
	@Pattern(regexp = "(^$|[0-9]{12})", message="Card number has to be 12 digits only")
	@Schema(
			description = "Card number of customer", 
			example="123456789012"
			)
	private String cardNumber;
	
	private String cardType;
	
	@Positive(message = "Card Limit should be greater than zero")
	private int cardLimit;
	
	@PositiveOrZero(message = "Amount used should be equal to or greater than zero")
	private int amountUsed;
	
	@PositiveOrZero(message = "Amount available should be equal to or greater than zero")
	private int amountAvailable;
}
