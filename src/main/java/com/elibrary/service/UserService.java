package com.elibrary.service;

import java.util.List;

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

	@Override
	public User findByID(Long id) {
		User user = (User) repository.findOne(id);
		return user;
	}

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
	@Override
	public User create(User user) {
		repository.save(user);
		return user;
	}

	@Override
	public List<User> findAllAdmins() {
		List<User> users = (List<User>) repository.findAllAdmins();
		return users;
	}

	@Override
	public List<User> findAllBorrowers() {
		List<User> users = (List<User>) repository.findAllBorrowers();
		return users;
	}

	@Override
	public List<User> findAllLibrarians() {
		List<User> users = (List<User>) repository.findAllLibrarians();
		return users;
	}

	@Override
	public List<User> findByType(Class type) {
		List<User> users = (List<User>) repository.findByType(type);
		return users;
	}
	
	@Override
	public void deleteById(Long id) {
		repository.delete(id);
	}
	
	@Override
	public void delete(User user) {
		repository.delete(user);
	}

	@Override
	public void update(User user) {
		repository.save(user);
	}
}
