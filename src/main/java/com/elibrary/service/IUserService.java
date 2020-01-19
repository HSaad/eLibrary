package com.elibrary.service;

import java.util.List;
import com.example.*;

public interface IUserService {
	List<User> findAllOrderedByNameDescending();

}
