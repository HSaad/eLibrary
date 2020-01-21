package com.elibrary.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elibrary.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAllOrderedDescending();
	List<User> findAll();
	User findByUsername(String username);
	User findByEmail(@Param("email") String email);		
	List<User> findAllAdmins();
	List<User> findAllBorrowers();
	List<User> findAllLibrarians();
	List<User> findByType(Class type);
}
