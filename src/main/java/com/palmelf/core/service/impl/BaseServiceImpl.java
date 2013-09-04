package com.palmelf.core.service.impl;

import com.palmelf.core.dao.GenericDao;
import com.palmelf.core.service.BaseService;

public class BaseServiceImpl<T> extends GenericServiceImpl<T, Long> implements
		BaseService<T> {
	public BaseServiceImpl(GenericDao<?, ?> dao) {
		super(dao);
	}
}
