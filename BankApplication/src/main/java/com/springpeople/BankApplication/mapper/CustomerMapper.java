package com.springpeople.BankApplication.mapper;

import com.springpeople.BankApplication.dto.CustomerDto;
import com.springpeople.BankApplication.entity.Customer;

public class CustomerMapper {
	public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
		customerDto.setCustomerName(customer.getCustomerName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		
		return customerDto;
	}
	
	public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		
		return customer;
	}
}
