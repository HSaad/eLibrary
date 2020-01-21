package com.elibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.dao.LoanRepository;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User;

@Service
public class LoanService implements ILoanService{

	@Autowired
	private LoanRepository repository;
	
	@Override
	public List<Loan> findAll() {
		List<Loan> loans = (List<Loan>) repository.findAll();
		return loans;
	}

	@Override
	public Loan findByUserAndItem(User user, LibraryItem item) {
		Loan loan = (Loan) repository.findByUserAndItem(user, item);
		return loan;
	}

	@Override
	public List<Loan> findByItem(LibraryItem item) {
		List<Loan> loans = (List<Loan>) repository.findByItem(item);
		return loans;
	}

	@Override
	public List<Loan> findByUser(User user) {
		List<Loan> loans = (List<Loan>) repository.findByUser(user);
		return loans;
	}

	@Override
	public List<Loan> findAllCurrentLoansByUser(User user) {
		List<Loan> loans = (List<Loan>) repository.findAllCurrentLoansByUser(user);
		return loans;
	}

	@Override
	public List<Loan> findAllCurrentLoansByItem(LibraryItem item) {
		List<Loan> loans = (List<Loan>) repository.findAllCurrentLoansByItem(item);
		return loans;
	}

	@Override
	public List<Loan> findAllCurrentLoans() {
		List<Loan> loans = (List<Loan>) repository.findAllCurrentLoans();
		return loans;
	}
	
	@Override
	public Loan create(Loan loan) {
		repository.save(loan);
		return loan;
	}
}