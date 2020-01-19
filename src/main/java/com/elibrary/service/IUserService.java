package com.elibrary.service;

import java.util.List;

import com.elibrary.model.*;

public interface IUserService {
	List<User> findAllOrderedDescending();
	List<User> findAll();
	User findByID(Long id);
	User findByUsername(String username);
	User findByEmail(String email);	
	User create(User user);
//	User readByUsername(String username);
//	User readByEmail(String email);		
//	List<User> readAllAdmins();
//	List<User> readAllBorrowers();
//	List<User> readAllLibrarians();
//	List<User> readByType(Class type);
}
