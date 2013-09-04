package com.palmelf.core.dao;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.paging.PagingBean;

import java.io.Serializable;
import java.util.List;

public abstract interface GenericDao<T, PK extends Serializable> {
	public abstract T save(T paramT);

	/**
	 * 使用merge方法，如果数据库中有该记录，则更新该记录，如果不存在该记录，则进行insert操作。
	 * 使用update的话，会无条件执行update，
	 * 
	 * @param paramT
	 * @return
	 */
	public abstract T merge(T paramT);

	public abstract T get(PK paramPK);

	public abstract void remove(PK paramPK);

	public abstract void remove(T paramT);

	/**
	 * 把指定的缓冲对象进行清除
	 * 
	 * @param paramT
	 */
	public abstract void evict(T paramT);

	public abstract List<T> getAll();

	public abstract List<T> getAll(PagingBean paramPagingBean);

	public abstract List<T> getAll(QueryFilter paramQueryFilter);

	public abstract List<T> find(String paramString,
			Object[] paramArrayOfObject, PagingBean paramPagingBean);

	public abstract List<T> findByHql(String paramString,
			Object[] paramArrayOfObject);

	public abstract List<T> findByHql(String paramString,
			Object[] paramArrayOfObject, PagingBean paramPagingBean);

	public abstract List<T> findByHql(String paramString,
			Object[] paramArrayOfObject, int paramInt1, int paramInt2);

	public abstract void flush();
}
