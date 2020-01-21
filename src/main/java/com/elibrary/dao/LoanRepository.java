package com.elibrary.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long>{
	
	List<Loan> findAll();
	Loan findByUserAndItem(User user, LibraryItem item);
	List<Loan> findByItem(LibraryItem item);
	List<Loan> findByUser(User user);
	List<Loan> findAllCurrentLoansByUser(User user);
	List<Loan> findAllCurrentLoansByItem(LibraryItem item);
	List<Loan> findAllCurrentLoans();
}
