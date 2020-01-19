package com.elibrary.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.CrudRepository;

import com.example.User;

public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findAllOrderedByNameDescending();
//	User readByUsername(String username);
//	User_JPA readByEmail(String email);		
//	List<User_JPA> readAllAdmins();
//	List<User_JPA> readAllBorrowers();
//	List<User_JPA> readAllLibrarians();
//	List<User_JPA> readByType(Class type);
}
