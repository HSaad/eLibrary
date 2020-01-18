package com.elibrary.controller;

import com.elibrary.dao.User_JPA_DAOI;
import com.elibrary.exceptions.UserDataException;
import com.elibrary.model.User_JPA;

public class AuthenticationController {
	private User_JPA_DAOI userDao;

	// TODO delete this setter when introducing spring and dependency injection
	public void setUserDao(User_JPA_DAOI userDao) {
		this.userDao = userDao;
	}

	public User_JPA register(String username, String password, String firstname, String lastname, String email)
			throws UserDataException {
		if (username.length() == 0 || username.indexOf(" ") < 0)// Add for validations as we think of them, maybe use a
																// regular expression to verify alphanumeric?
			throw new UserDataException("Invalid Username format");
		if (userDao.readByUsername(username) != null)
			throw new UserDataException("Username is not unique");
		if (password.length() < 6)
			throw new UserDataException("Password length too short");

		User_JPA u = new User_JPA().setUsername(username).setPassword(password).setFirstName(firstname).setLastName(lastname)
				.setEmail(email);
		userDao.create(u);
		return u;

		//return null;
	}

	public User_JPA login(String username, String password) {
		User_JPA user = userDao.readByUsername(username);
		if (user != null && user.getPassword().equals(password))
			return user;

		return null;
	}

	public void changePassword(User_JPA u, String oldPw, String newPw, String confPw) throws UserDataException {

		if (!newPw.equals(confPw))
			throw new UserDataException("Passwords do not match");

		if (!oldPw.equals(u.getPassword()))
			throw new UserDataException("Invalid existing password");

		u.setPassword(newPw);
		userDao.update(u);
	}
}
