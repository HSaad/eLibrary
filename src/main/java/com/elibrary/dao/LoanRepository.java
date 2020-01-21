package com.elibrary.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long>{
	
	List<Loan> findAll();
	Loan findByUserAndItem(@Param("user") User user, @Param("item") LibraryItem item);
	List<Loan> findByItem(@Param("item") LibraryItem item);
	List<Loan> findByUser(@Param("user") User user);
	List<Loan> findAllCurrentLoansByUser(@Param("user") User user);
	List<Loan> findAllCurrentLoansByItem(@Param("item") LibraryItem item);
	List<Loan> findAllCurrentLoans();
}
