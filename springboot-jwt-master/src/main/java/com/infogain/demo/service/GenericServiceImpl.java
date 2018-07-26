package com.infogain.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.demo.dao.IGenericDao;

@Service("genericService")
public class GenericServiceImpl<T> implements IGenericService<T> {

	@Autowired
	private IGenericDao<T> iGenericDao;

	@Override
	public T find(final T entity,Long id) { 
		return iGenericDao.find(entity,id);
	}

	@Override
	public T find(final T entity,String condition) { 
		return iGenericDao.find(entity,condition);
	}

	@Override
	public List<T> fetchAll(final T entity) { 
		return iGenericDao.fetchAll(entity);
	}

	@Override
	public List<T> fetchAll(final T entity,String condition) { 
		return iGenericDao.fetchAll(entity,condition);
	}

	@Override
	public void create(final T entity) {
		iGenericDao.create(entity);
	}

	@Override
	public void update(final T entity) {
		iGenericDao.update(entity);
	}

	@Override
	public void delete(final T entity) {
		iGenericDao.delete(entity);
	}

	
}
