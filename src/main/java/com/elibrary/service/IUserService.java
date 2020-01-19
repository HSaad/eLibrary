package com.elibrary.service;

import java.util.List;

import com.elibrary.model.*;

public interface IUserService {
	List<User> findAllOrderedByNameDescending();

}
