package com.springpeople.BankApplication.repository;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springpeople.BankApplication.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	//select * from customer where mobile_number = ""
	Optional<Customer> findByMobileNumber(String mobile);
	
	Optional<Customer> findByCustomerId(Long id);
	
	Optional<Customer> findByEmail(String email);
	
	void deleteByCustomerId(Long id);
	
//	@Modifying
//	@Query("update Customer c set c.email = :email where c.customer_id = :id")
//	int updateEmail(@Param("email") String email, @Param("id") Long id);
	
	//select * from customer where customer_id between(start, end)
	//List<Customer> findByCustomerIdBetween(long start, long end);
}
