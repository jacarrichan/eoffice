package com.palmelf.core.service;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.paging.PagingBean;

import java.io.Serializable;
import java.util.List;

public abstract interface GenericService<T, PK extends Serializable> {
	public abstract T save(T paramT);

	public abstract T merge(T paramT);

	public abstract void evict(T paramT);

	public abstract T get(PK paramPK);

	public abstract List<T> getAll();

	public abstract List<T> getAll(PagingBean paramPagingBean);

	public abstract List<T> getAll(QueryFilter paramQueryFilter);

	public abstract void remove(PK paramPK);

	public abstract void remove(T paramT);

	public abstract void flush();
}
