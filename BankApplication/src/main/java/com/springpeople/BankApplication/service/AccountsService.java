package com.springpeople.BankApplication.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpeople.BankApplication.dto.AccountsDto;
import com.springpeople.BankApplication.dto.CustomerDto;
import com.springpeople.BankApplication.entity.Accounts;
import com.springpeople.BankApplication.entity.Customer;
import com.springpeople.BankApplication.exception.CustomerAlreadyExistsException;
import com.springpeople.BankApplication.exception.ResourceNotFoundException;
import com.springpeople.BankApplication.mapper.AccountsMapper;
import com.springpeople.BankApplication.mapper.CustomerMapper;
import com.springpeople.BankApplication.repository.AccountsRepository;
import com.springpeople.BankApplication.repository.CustomerRepository;

@Service
/**
 * AccountsService Class
 * @author Pallavi
 */
public class AccountsService {

	@Autowired
	private AccountsRepository accountsRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
//	@Autowired
//	public AccountsService(AccountsRepository accountsRepo, CustomerRepository customerRepo) {
//		this.accountsRepo = accountsRepo;
//		this.customerRepo = customerRepo;
//	}
	
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optCustomer = customerRepo.findByMobileNumber(customer.getMobileNumber());
		
		if(optCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with mobile number " 
					+ customer.getMobileNumber());
		}
		
		Customer savedCustomer = customerRepo.save(customer);
		accountsRepo.save(createNewAccount(savedCustomer));
		
//		Customer savedCustomer = customerRepo.save(customer);
//		accountsRepo.save(createNewAccount(savedCustomer));
	}
	
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		
		long randAccountNumber = 1000000000L + new Random().nextInt(900000000);
		
		newAccount.setAccountNumber(randAccountNumber);
		newAccount.setAccountType("Savings");
		newAccount.setBranchAddress("Bangalore");
		
		
		return newAccount;
	}
	
	public List<Accounts> listAllAccounts() {
		return accountsRepo.findAll();
	}
	
	public CustomerDto retrieveAccount(String mobile) {
		Customer customer = customerRepo.findByMobileNumber(mobile).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobile));
		
		Accounts account = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		
		CustomerDto cDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		cDto.setAccountDetails(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
		
		return cDto;
	}
	
	public String updateAccountDetails(String type, String mobile) {
		Customer customer = customerRepo.findByMobileNumber(mobile).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobile));
		
		Accounts account = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		
		account.setAccountType(type);
		
		int res = accountsRepo.updateAccountType(account.getAccountType(), account.getAccountNumber());
		
		if (res > 0) {
			return "Account updated successfully";
		}
		
		return "Account update unsuccessful";
	}
	
	public boolean deleteAccount(String mobile) {
		Customer customer = customerRepo.findByMobileNumber(mobile).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobile));
		
		accountsRepo.deleteByCustomerId(customer.getCustomerId());
//		customerRepo.deleteByCustomerId(customer.getCustomerId());
		customerRepo.deleteById(customer.getCustomerId());
		return true;
	}
	
}
