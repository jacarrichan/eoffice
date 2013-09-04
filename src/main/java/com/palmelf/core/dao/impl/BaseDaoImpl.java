package com.palmelf.core.dao.impl;

import com.palmelf.core.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends GenericDaoImpl<T, Long> implements
		BaseDao<T> {
	public BaseDaoImpl(Class persistType) {
		super(persistType);
	}
}
