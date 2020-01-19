package com.elibrary.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elibrary.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User readByUsername(String username);
	User readByEmail(String email);		
//	List<User_JPA> readAllAdmins();
//	List<User_JPA> readAllBorrowers();
//	List<User_JPA> readAllLibrarians();
//	List<User_JPA> readByType(Class type);
}
