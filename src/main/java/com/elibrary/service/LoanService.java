package com.elibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.dao.LoanRepository;
import com.elibrary.model.Loan;

@Service
public class LoanService implements ILoanService{

	@Autowired
	private LoanRepository repository;
	
	@Override
	public List<Loan> findAll() {
		List<Loan> users = (List<Loan>) repository.findAll();
		return users;
	}
}