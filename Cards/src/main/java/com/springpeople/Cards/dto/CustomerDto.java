package com.springpeople.Cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
		name = "Customer",
		description = "Holds Customer Information"
		)
public class CustomerDto {

	@NotEmpty(message = "Customer name is mandatory")
	@Size(min = 6, max = 30, message = "Customer name should be between 6 and 30 characters long")
	private String customerName;
	
	
	@Email(message = "Email should be valid")
	private String email;
	
	@Pattern(regexp = "(^$|[0-9]{10})", message="Account number has to be 10 digits only")
	private String mobileNumber;
	
	@Schema(
			description = "Account Details for this customer"
			)
	private AccountsDto accountDetails;
}
