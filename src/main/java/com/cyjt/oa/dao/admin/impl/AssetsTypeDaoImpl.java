package com.cyjt.oa.dao.admin.impl;

import java.util.List;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.admin.AssetsTypeDao;
import com.cyjt.oa.model.admin.AssetsType;

public class AssetsTypeDaoImpl extends BaseDaoImpl<AssetsType> implements
		AssetsTypeDao {
	public AssetsTypeDaoImpl() {
		super(AssetsType.class);
	}

	@Override
	public List find(String paramString, Object[] paramArrayOfObject,
			PagingBean paramPagingBean) {
		// TODO Auto-generated method stub
		return null;
	}
}
