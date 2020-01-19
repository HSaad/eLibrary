package com.elibrary.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.dao.UserRepository;
import com.elibrary.model.User;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public List<User> findAll() {
		List<User> users = (List<User>) repository.findAll();
		return users;
	}
	
	@Override
	public List<User> findAllOrderedDescending() {
		List<User> users = (List<User>) repository.findAllOrderedDescending();
		return users;
	}

//	@Override
//	public User findByID(Long id) {
//		User user = (User) repository.findByID(id);
//		return user;
//	}

	@Override
	public User findByUsername(String username) {
		User user = (User) repository.findByUsername(username);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = (User) repository.findByEmail(email);
		return user;
	}
	
	//Creates new user 
	public User create(User user) {
		repository.save(user);
		return user;
	}
}
