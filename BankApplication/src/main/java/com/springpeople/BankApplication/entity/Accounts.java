package com.springpeople.BankApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends MappedClass{

	@Id
	@Column(name = "account_number")
	private Long accountNumber;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "branch_address")
	private String branchAddress;
}
