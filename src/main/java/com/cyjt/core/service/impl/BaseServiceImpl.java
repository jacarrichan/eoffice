package com.cyjt.core.service.impl;

import com.cyjt.core.dao.GenericDao;
import com.cyjt.core.service.BaseService;

public class BaseServiceImpl<T> extends GenericServiceImpl<T, Long> implements
		BaseService<T> {
	public BaseServiceImpl(GenericDao<?, ?> dao) {
		super(dao);
	}
}
