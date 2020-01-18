package com.elibrary.dao;

import com.elibrary.model.IStorable;

public interface IDeletable<T extends IStorable> {
	void delete(T t);
}
