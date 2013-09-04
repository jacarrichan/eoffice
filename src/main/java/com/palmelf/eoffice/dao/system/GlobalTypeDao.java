package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.GlobalType;

import java.util.List;

public abstract interface GlobalTypeDao extends BaseDao<GlobalType> {
	public abstract List<GlobalType> getByParentIdCatKey(Long paramLong,
			String paramString);

	public abstract Integer getCountsByParentId(Long paramLong);

	public abstract List<GlobalType> getByParentId(Long paramLong);

	public abstract List<GlobalType> getByPath(String paramString);

	public abstract GlobalType findByTypeName(String paramString);
}
