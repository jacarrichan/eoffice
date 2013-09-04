package com.palmelf.eoffice.dao.admin.impl;

import java.util.List;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.admin.AssetsTypeDao;
import com.palmelf.eoffice.model.admin.AssetsType;

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
