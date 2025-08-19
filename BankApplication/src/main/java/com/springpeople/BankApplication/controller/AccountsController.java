package com.springpeople.BankApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springpeople.BankApplication.constants.AccountsConstants;
import com.springpeople.BankApplication.dto.CustomerDto;
import com.springpeople.BankApplication.dto.ErrorResponseDto;
import com.springpeople.BankApplication.dto.ResponseDto;
import com.springpeople.BankApplication.entity.Customer;
import com.springpeople.BankApplication.service.AccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(
		name = "CRUD REST API for Accounts",
		description = "REST APIs for CRUD operations for Accounts"
		
		)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

	@Autowired
	private AccountsService service;
	
	@Operation(
			summary = "Create Account REST API",
			description = "creates a new customer and an account for the customer using REST API"
			)
	@ApiResponses({
				@ApiResponse(
					responseCode = "201",
					description = "HTTP status CREATED"
				),
				@ApiResponse(
						responseCode = "500",
						description = "INTERNAL SERVER ERROR - Http Status",
						content = @Content(
								schema = @Schema(implementation = ErrorResponseDto.class)
								)
				),
				@ApiResponse (
						responseCode = "400",
						description = "Http Status - Bad Request (invalid data)",
						content = @Content(
								schema = @Schema(implementation = ErrorResponseDto.class)
								)
						)
			})
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customer) {
		service.createAccount(customer);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
		
//		service.createAccount(customer);
//		return "account created";
	}
	
//	@GetMapping("/fetch/{mobile}")	
//	public ResponseEntity<CustomerDto> retrieveAccountDetails(@PathVariable String mobile) {
//		CustomerDto customerDto = service.retrieveAccount(mobile);
//		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
//	}
	
	@GetMapping("/fetch")	
	public ResponseEntity<CustomerDto> retrieveAccountDetails(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits") String mobile) {
		CustomerDto customerDto = service.retrieveAccount(mobile);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	
	@PatchMapping("/set/{type}/{mobile}")
	public ResponseEntity<String> updateAccount(@PathVariable String type, @PathVariable String mobile) {
		String str = service.updateAccountDetails(type, mobile);
		return ResponseEntity.status(HttpStatus.OK).body(str);
	}
	
	@DeleteMapping("/delete/{mobile}")
	public ResponseEntity<ResponseDto> deleteAccount(@PathVariable String mobile) {
		boolean isDeleted = service.deleteAccount(mobile);
		
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417));
		}
	}
	
}
