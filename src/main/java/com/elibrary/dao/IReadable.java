package com.elibrary.dao;

import java.util.List;

import com.elibrary.model.IStorable;

public interface IReadable <T extends IStorable>{
	T readById(int id);
	List<T> readAll();
}
