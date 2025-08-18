package com.springpeople.BankApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private String customerName;
	private String email;
	private String mobileNumber;
	private AccountsDto accountDetails;
}
