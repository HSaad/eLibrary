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
	public List<User> findAllOrderedByNameDescending() {
		// TODO Auto-generated method stub
		List<User> users = (List<User>) repository.findAllOrderedByNameDescending();
		return users;
	}
	
	@Override
	public List<User> readAll() {
		// TODO Auto-generated method stub
		List<User> users = (List<User>) repository.findAll();
		return users;
	}

	@Override
	public User readByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User readByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> readAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> readAllBorrowers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> readAllLibrarians() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> readByType(Class type) {
		// TODO Auto-generated method stub
		return null;
	}
}
