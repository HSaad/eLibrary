package com.elibrary.dao;

import com.elibrary.model.IStorable;

public interface ICreatable<T extends IStorable> {
	T create(T t);
}
