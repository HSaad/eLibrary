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
@Table(name="loan")
@NamedQueries({
	//find all loans
	@NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l"),
	//find loan by itemID and userID
	@NamedQuery(name = "Loan.findByUserAndItem", query = "SELECT l FROM Loan l WHERE l.user = :user AND l.item = :item AND l.returnedDate IS NULL"),
	//find all loans on a specific library item
	@NamedQuery(name = "Loan.findByItem", query = "SELECT l FROM Loan l WHERE l.item = :item"),
	//find all loans by userID (loan history)
	@NamedQuery(name = "Loan.findByUser", query = "SELECT l FROM Loan l WHERE l.user = :user AND l.returnedDate IS NOT NULL"),
	//find all current loans by userID 
	@NamedQuery(name = "Loan.findAllCurrentLoansByUser", query = "SELECT l from Loan l WHERE l.user = :user AND l.returnedDate IS NULL"),
	//find all current loans by itemID 
	@NamedQuery(name = "Loan.findAllCurrentLoansByItem", query = "SELECT l from Loan l WHERE l.item = :item AND l.returnedDate IS NULL"),
	//find all current loans
	@NamedQuery(name = "Loan.findAllCurrentLoans", query = "SELECT l from Loan l WHERE l.returnedDate IS NULL")
})
public class Loan{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private LibraryItem item;
	
	@ManyToOne
	private User user;

	private LocalDate borrowedDate;	
	private LocalDate returnedDate;
	
	public Loan() {
		super();
	}

	public Loan(Long id, LibraryItem item, User user, LocalDate borrowedDate, LocalDate returnedDate) {
		super();
		this.id = id;
		this.item = item;
		this.user = user;
		this.borrowedDate = borrowedDate;
		this.returnedDate = returnedDate;
	}
	
	public Loan(LibraryItem item, User user, LocalDate borrowedDate, LocalDate returnedDate) {
		super();
		this.item = item;
		this.user = user;
		this.borrowedDate = borrowedDate;
		this.returnedDate = returnedDate;
	}

	public Long getId() {
		return id;
	}

	public Loan setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public Loan setUser(User user) {
		this.user = user;
		return this;
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
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Loan [loanID=" + id + ", itemID=" + item + ", userID=" + user + ", borrowedDate=" + borrowedDate
				+ ", returnedDate=" + returnedDate + "]";
	}
}
