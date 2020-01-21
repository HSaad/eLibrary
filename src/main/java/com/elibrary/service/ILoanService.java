package com.elibrary.service;

import java.util.List;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;

public interface ILoanService {
	List<Loan> findAll();
	Loan findByUserAndItem(User user, LibraryItem item);
	List<Loan> findByItem(LibraryItem item);
	List<Loan> findByUser(User user);
	List<Loan> findAllCurrentLoansByUser(User user);
	List<Loan> findAllCurrentLoansByItem(LibraryItem item);
	List<Loan> findAllCurrentLoans();
}
