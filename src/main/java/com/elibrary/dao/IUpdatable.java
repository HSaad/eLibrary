package com.elibrary.dao;

import com.elibrary.model.IStorable;

public interface IUpdatable<T extends IStorable> {
	void update(T t); 
}
