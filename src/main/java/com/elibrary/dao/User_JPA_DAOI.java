package com.elibrary.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import com.elibrary.model.User_JPA;

public interface User_JPA_DAOI extends ICreatable<User_JPA>, IReadable<User_JPA>, 
										IUpdatable<User_JPA>, IDeletable<User_JPA>{
											
	User_JPA readByUsername(String username);
	User_JPA readByEmail(String email);		
	List<User_JPA> readAllAdmins();
	List<User_JPA> readAllBorrowers();
	List<User_JPA> readAllLibrarians();
	List<User_JPA> readByType(Class type);
}
