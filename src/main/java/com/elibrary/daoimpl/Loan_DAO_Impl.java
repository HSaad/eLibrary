package com.elibrary.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.elibrary.dao.Loan_DAOI;
import com.elibrary.model.LibraryItem;
import com.elibrary.model.Loan;
import com.elibrary.model.User_JPA;

public class Loan_DAO_Impl implements Loan_DAOI{
	private DbConnection connection;
	
	public Loan_DAO_Impl() {
		super();
		connection = DbConnection.getInstance();
	}

	@Override
	public Loan create(Loan loan) {
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin(); 
			em.persist(loan);
		em.getTransaction().commit();
		em.close();
		
		return loan;
	}

	@Override
	public Loan readById(int id) {
		String temp = Integer.toString(id);
		
		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findByID");
		q.setParameter("loanID", temp);
		List<Loan> loan = q.getResultList();
		em.close();
		
		return loan.get(0);
	}

	@Override
	public List<Loan> readAll() {
		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findAll");
		List<Loan> loans = q.getResultList();
		em.close();
		
		return loans;
	}

	@Override
	public void update(Loan loan) {
		EntityManager em = connection.getEntityManager();
		Loan foundLoan = em.find(Loan.class, loan.getLoanID());
		em.getTransaction().begin();

			foundLoan.setBorrowedDate(loan.getBorrowedDate());
			foundLoan.setReturnedDate(loan.getReturnedDate());
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Loan> readByUserID(User_JPA user) {

		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findByUser");
		q.setParameter("user", user);
		List<Loan> loans = q.getResultList();
		em.close();
		
		return loans;
	}

	@Override
	public List<Loan> readByItemID(LibraryItem item) {
		
		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findByItem");
		q.setParameter("item", item);
		List<Loan> loans = q.getResultList();
		em.close();
		
		return loans;
	}

	@Override
	public List<Loan> readAllCurrentLoansByUser(User_JPA user) {
		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findAllCurrentLoansByUser");
		q.setParameter("user", user);
		List<Loan> loans = q.getResultList();
		em.close();
		
		return loans;
	}

	@Override
	public List<Loan> readAllCurrentLoansByItem(LibraryItem item) {

		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findAllCurrentLoansByItem");
		q.setParameter("item", item);
		List<Loan> loans = q.getResultList();
		em.close();
		
		return loans;
	}

	@Override
	public List<Loan> readAllCurrentLoans() {
		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findAllCurrentLoans");
		List<Loan> loans = q.getResultList();
		em.close();
		
		return loans;
	}

	@Override
	public Loan readByUserAndItem(User_JPA user, LibraryItem item) {
		EntityManager em = connection.getEntityManager();
		TypedQuery<Loan> q = (TypedQuery<Loan>) em.createNamedQuery("loan.findByUserAndItem");
		q.setParameter("item", item);
		q.setParameter("user", user);
		List<Loan> loans = q.getResultList();
		em.close();
		
		if(loans.isEmpty()) { //maybe throw exception??
			return null;
		}
		
		return loans.get(0);
	}

	@Override
	public void delete(Loan l) {
		EntityManager em = connection.getEntityManager();
		Loan foundLoan = em.find(Loan.class, l.getLoanID());
		
		em.getTransaction().begin();
			em.remove(foundLoan);
		em.getTransaction().commit();
		em.close();
	}

}
