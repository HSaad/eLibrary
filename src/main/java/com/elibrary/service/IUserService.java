package com.elibrary.service;

import java.util.List;

import com.elibrary.model.*;

public interface IUserService {
	List<User> findAllOrderedDescending();
	List<User> findAll();
	User findByUsername(String username);
	User findByEmail(String email);	
	User create(User user);	
	List<User> findAllAdmins();
	List<User> findAllBorrowers();
	List<User> findAllLibrarians();
	List<User> findByType(Class type);
	User findByID(Long id);
}
