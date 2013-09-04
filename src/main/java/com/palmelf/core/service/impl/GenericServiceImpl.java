package com.palmelf.core.service.impl;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.dao.GenericDao;
import com.palmelf.core.service.GenericService;
import com.palmelf.core.web.paging.PagingBean;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenericServiceImpl<T, PK extends Serializable> implements
		GenericService<T, PK> {
	protected Log logger = LogFactory.getLog(GenericServiceImpl.class);

	protected GenericDao<T, Serializable> dao = null;

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}

	public GenericServiceImpl(GenericDao dao) {
		this.dao = dao;
	}

	public T get(PK id) {
		return this.dao.get(id);
	}

	public T save(T entity) {
		return this.dao.save(entity);
	}

	public T merge(T entity) {
		return this.dao.merge(entity);
	}

	public void evict(T entity) {
		this.dao.evict(entity);
	}

	public List<T> getAll() {
		return this.dao.getAll();
	}

	public List<T> getAll(PagingBean pb) {
		return this.dao.getAll(pb);
	}

	public List<T> getAll(QueryFilter filter) {
		return this.dao.getAll(filter);
	}

	public void remove(PK id) {
		this.dao.remove(id);
	}

	public void remove(T entity) {
		this.dao.remove(entity);
	}

	public void flush() {
		this.dao.flush();
	}
}
