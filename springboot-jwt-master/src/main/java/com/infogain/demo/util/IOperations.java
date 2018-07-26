package com.infogain.demo.util;

import java.util.List;

public interface IOperations<T> {

	T find(final T entity,final Long id);
	
	T find(final T entity,String condition);

	List<T> fetchAll(final T entity);
	
	List<T> fetchAll(final T entity,String condition);

	void create(final T entity);

	void update(final T entity);

	void delete(final T entity); 
}
