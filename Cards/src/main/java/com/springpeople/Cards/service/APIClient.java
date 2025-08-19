package com.springpeople.Cards.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springpeople.Cards.dto.CustomerDto;

@FeignClient(value = "BankApplication", url = "http://localhost:8092/api")
public interface APIClient {

	@GetMapping("/fetch")
	CustomerDto getCustomerDetails(@RequestParam String mobile);
}
