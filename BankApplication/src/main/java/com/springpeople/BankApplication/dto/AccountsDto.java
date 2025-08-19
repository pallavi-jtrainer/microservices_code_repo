package com.springpeople.BankApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
		name="Accounts",
		description="Holds Accounts information"
		)
public class AccountsDto {
	@NotEmpty(message = "Account number should not be null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message="Account number has to be 10 digits only")
	@Schema(
			name="AccountNumber", 
			description="Account Number for a customer",
			example="1234567890"
			)
	private Long accountNumber;
	
	@NotEmpty(message = "Account Type cannot be null or empty")
	@Schema(
			name="AccountType",
			description = "Type of Account - Savings or Current",
			example="Savings"
			)
	private String accountType;
	
	@NotEmpty(message="Branch Address cannot be null or empty")
	@Schema(
			name = "BranchAddress",
			description = "Address of branch"
			)
	private String branchAddress;
}
