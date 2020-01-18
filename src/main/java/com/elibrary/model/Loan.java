package com.elibrary.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Loan")
@NamedQueries({
	//find all loans
	@NamedQuery(name = "loan.findAll", query = "SELECT l FROM Loan l"),
	//find library item by itemID
	@NamedQuery(name = "loan.findByID", query = "SELECT l FROM Loan l WHERE l.loanID = :loanID"),
	//find loan by itemID and userID
	@NamedQuery(name = "loan.findByUserAndItem", query = "SELECT l FROM Loan l WHERE l.user = :user AND l.item = :item AND l.returnedDate IS NULL"),
	//find all loans on a specific library item
	@NamedQuery(name = "loan.findByItem", query = "SELECT l FROM Loan l WHERE l.item = :item"),
	//find all loans by userID (loan history)
	@NamedQuery(name = "loan.findByUser", query = "SELECT l FROM Loan l WHERE l.user = :user AND l.returnedDate IS NOT NULL"),
		
	//find all current loans by userID 
	@NamedQuery(name = "loan.findAllCurrentLoansByUser", query = "SELECT l from Loan l WHERE l.user = :user AND l.returnedDate IS NULL"),
	//find all current loans by itemID 
	@NamedQuery(name = "loan.findAllCurrentLoansByItem", query = "SELECT l from Loan l WHERE l.item = :item AND l.returnedDate IS NULL"),
	//find all current loans
	@NamedQuery(name = "loan.findAllCurrentLoans", query = "SELECT l from Loan l WHERE l.returnedDate IS NULL")
})
public class Loan implements IStorable{

	@Id
	@Column(name="loanID")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="loanID") //fix id generation (continues from other table sequence)
	@SequenceGenerator(name="loanID", sequenceName="loanID")
	private int loanID;
	
	@ManyToOne
	private LibraryItem item;
	
	@ManyToOne
	private User_JPA user;
	
	@Column(name="borrowedDate")
	private LocalDate borrowedDate;
	
	//Null for a returnedDate means the item is still checked out
	@Column(name="returnedDate") 
	private LocalDate returnedDate;
	
	public Loan() {
		super();
	}

	public Loan(int loanID, LibraryItem item, User_JPA user, LocalDate borrowedDate, LocalDate returnedDate) {
		super();
		this.loanID = loanID;
		this.item = item;
		this.user = user;
		this.borrowedDate = borrowedDate;
		this.returnedDate = returnedDate;
	}
	
	public Loan(LibraryItem item, User_JPA user, LocalDate borrowedDate, LocalDate returnedDate) {
		super();
		this.item = item;
		this.user = user;
		this.borrowedDate = borrowedDate;
		this.returnedDate = returnedDate;
	}

	public int getLoanID() {
		return loanID;
	}

	public Loan setLoanID(int loanID) {
		this.loanID = loanID;
		return this;
	}
	
	public LocalDate getBorrowedDate() {
		return borrowedDate;
	}
	
	public Loan setBorrowedDate(LocalDate borrowedDate) {
		this.borrowedDate = borrowedDate;
		return this;
	}
	
	public LocalDate getReturnedDate() {
		return returnedDate;
	}
	
	public Loan setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
		return this;
	}
	
	public LibraryItem getItem() {
		return item;
	}

	public Loan setItem(LibraryItem item) {
		this.item = item;
		return this;
	}

	public User_JPA getUser() {
		return user;
	}

	public Loan setUser(User_JPA user) {
		this.user = user;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + loanID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (loanID != other.loanID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Loan [loanID=" + loanID + ", itemID=" + item + ", userID=" + user + ", borrowedDate=" + borrowedDate
				+ ", returnedDate=" + returnedDate + "]";
	}
}
