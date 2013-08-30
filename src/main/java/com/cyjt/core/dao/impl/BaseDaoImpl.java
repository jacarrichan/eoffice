package com.cyjt.core.dao.impl;

import com.cyjt.core.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends GenericDaoImpl<T, Long> implements
		BaseDao<T> {
	public BaseDaoImpl(Class persistType) {
		super(persistType);
	}
}
