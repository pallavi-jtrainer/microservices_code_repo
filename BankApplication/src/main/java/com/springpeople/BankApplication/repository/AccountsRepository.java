package com.springpeople.BankApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springpeople.BankApplication.entity.Accounts;

import jakarta.transaction.Transactional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>{
	Optional<Accounts> findByCustomerId(Long id);
	
	List<Accounts> findAllByCustomerId(Long id);
	
	@Modifying
	@Transactional
	void deleteByCustomerId(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE ACCOUNTS SET ACCOUNT_TYPE = :type WHERE ACCOUNT_NUMBER = :number",nativeQuery = true)
	int updateAccountType(@Param("type") String type, @Param("number") Long number);
}
