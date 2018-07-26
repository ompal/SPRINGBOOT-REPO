package com.infogain.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("genericDao")
@Transactional
public class GenericDaoImpl<T> implements IGenericDao<T> {
 

	@PersistenceContext
	EntityManager entityManager; 
	 
	
	@Override
	public T find(final T entity,Long id) {
		return (T) entityManager.find(entity.getClass(), id);
	}

	@Override
	public T find(final T entity,String condition) {
		
		return (T) entityManager.createQuery("from " + entity.getClass().getName() + " " + condition).getSingleResult();
	}

	@Override
	public List<T> fetchAll(final T entity) {  
		return (List<T>) entityManager.createQuery("from " + entity.getClass().getName()).getResultList();
	}

	@Override
	public List<T> fetchAll(final T entity,String condition) { 
		return (List<T>) entityManager.createQuery("from " + entity.getClass().getName() + " " + condition).getResultList();
	}

	@Override
	public void create(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

}
