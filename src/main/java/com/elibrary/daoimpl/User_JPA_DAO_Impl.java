package com.elibrary.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.elibrary.dao.User_JPA_DAOI;
import com.elibrary.model.User_JPA;

public class User_JPA_DAO_Impl implements User_JPA_DAOI{
	private int userCount;
	private DbConnection connection;
	
	public User_JPA_DAO_Impl() {
		super();
		userCount = 0;
		connection = DbConnection.getInstance();
	}
	
	//Creates new user 
	@Override
	public User_JPA create(User_JPA user) {
		System.out.println(user);
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin(); 
			em.persist(user);
			
		em.getTransaction().commit();
		em.close();
		
		return user;
	}
	
	//Get user by id
	@Override
	public User_JPA readById(int id) {

		EntityManager em = connection.getEntityManager();
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findByID");
		q.setParameter("userID", id);
		List<User_JPA> user = q.getResultList();
		em.close();
		
		return user.get(0);
	}
	
	//Returns a list of all users
	@Override
	public List<User_JPA> readAll() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findAll");
		List<User_JPA> users = q.getResultList();
		em.close();
		
		return users;
	}

	//Updates/modifies user t
	@Override
	public void update(User_JPA user) {
		EntityManager em = connection.getEntityManager();
		User_JPA foundUser = em.find(User_JPA.class, user.getUserID());
		em.getTransaction().begin();
		
		if (user.getPassword() != null && !user.getPassword().equals(""))
			foundUser.setPassword(user.getPassword());
		if (user.getFirstName() != null && !user.getFirstName().equals(""))
			foundUser.setFirstName(user.getFirstName());
		if (user.getFirstName() != null && !user.getFirstName().equals(""))
			foundUser.setFirstName(user.getFirstName());

		em.getTransaction().commit();
		em.close();
	}
	
	//Deletes User from database
	@Override
	public void delete(User_JPA user) {
		EntityManager em = connection.getEntityManager();
		User_JPA foundUser = em.find(User_JPA.class, user.getUserID());
		
		em.getTransaction().begin();
		em.remove(foundUser);
		em.getTransaction().commit();
		em.close();
		
		userCount--;
	}
	
	//Returns the user by username (unique)
	@Override
	public User_JPA readByUsername(String username) {
		EntityManager em = connection.getEntityManager();
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findByUsername");
		q.setParameter("username", username);
		List<User_JPA> user = q.getResultList();
		em.close();
		
		return user.get(0);
	}
	
	//Gets user by email (unique)
	@Override
	public User_JPA readByEmail(String email) {
		EntityManager em = connection.getEntityManager();
		
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findByEmail");
		q.setParameter("email", email);
		List<User_JPA> user = q.getResultList();
		em.close();
		
		if(user.isEmpty()) { //maybe throw exception??
			return null;
		}
		
		return user.get(0);
	}
	
	//Returns a list of all users that are admins
	@Override
	public List<User_JPA> readAllAdmins() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findAllAdmins");
		List<User_JPA> users = q.getResultList();
		em.close();
		
		return users;
	}
	
	//Returns a list of all users that are only borrowers
	@Override
	public List<User_JPA> readAllBorrowers() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findAllBorrowers");
		List<User_JPA> users = q.getResultList();
		em.close();
		
		return users;
	}
	
	//Returns a list of all users that are librarians
	@Override
	public List<User_JPA> readAllLibrarians() {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findAllLibrarians");
		List<User_JPA> users = q.getResultList();
		em.close();
		
		return users;
	}
	
	//Returns a list of users with the same type parameter
	@Override
	public List<User_JPA> readByType(Class type) {
		EntityManager em = connection.getEntityManager(); 
		
		TypedQuery<User_JPA> q = (TypedQuery<User_JPA>) em.createNamedQuery("user.findByType");
		q.setParameter("type", type);
		List<User_JPA> users = q.getResultList();
		em.close();
		
		return users;
	}

}
