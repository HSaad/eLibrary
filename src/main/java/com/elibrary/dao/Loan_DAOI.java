package com.elibrary.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User_JPA;

public interface Loan_DAOI extends ICreatable<Loan>, IReadable<Loan>, 
									IUpdatable<Loan>, IDeletable<Loan>{ 
	
	//find loan by itemID and userID
	Loan readByUserAndItem(User_JPA user, LibraryItem item);
	//find all loans by userID (loan history)
	List<Loan> readByUserID(User_JPA user);
	//find all loans on a specific library item
	List<Loan> readByItemID(LibraryItem item);
		
	//find all current loans by userID (all library items that a user has checked out)
	List<Loan> readAllCurrentLoansByUser(User_JPA user);
	//find all current loans by itemID (all library items that are checked out)
	List<Loan> readAllCurrentLoansByItem(LibraryItem item);
	//find all current loans 
	List<Loan> readAllCurrentLoans();
}
