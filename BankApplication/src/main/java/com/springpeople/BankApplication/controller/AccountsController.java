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
import org.springframework.web.bind.annotation.PutMapping;
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
	
	 @Operation(
	            summary = "Fetch Account Details REST API",
	            description = "REST API to fetch Customer &  Account details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	@GetMapping("/fetch")	
	public ResponseEntity<CustomerDto> retrieveAccountDetails(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits") String mobile) {
		CustomerDto customerDto = service.retrieveAccount(mobile);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	
	 @Operation(
	            summary = "Update Account Details REST API",
	            description = "REST API to Account details based on mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "417",
	                    description = "Expectation Failed"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	@PatchMapping("/set/{type}/{mobile}")
	public ResponseEntity<String> updateAccount(@PathVariable String type, @PathVariable String mobile) {
		String str = service.updateAccountDetails(type, mobile);
		return ResponseEntity.status(HttpStatus.OK).body(str);
	}
	
	 
	 @Operation(
	            summary = "Update Account Details REST API",
	            description = "REST API to update Customer &  Account details based on a account number"
	    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = service.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417));
        }
    }
	 
	@Operation(
	            summary = "Delete Account & Customer Details REST API",
	            description = "REST API to delete Customer &  Account details based on a mobile number"
	    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
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
