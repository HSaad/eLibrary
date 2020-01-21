package com.elibrary.service;

import java.util.List;

import com.elibrary.model.Loan;

public interface ILoanService {
	List<Loan> findAll();
}
