package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.GlobalType;
import java.util.List;

public abstract interface GlobalTypeDao extends BaseDao<GlobalType> {
	public abstract List<GlobalType> getByParentIdCatKey(Long paramLong,
			String paramString);

	public abstract Integer getCountsByParentId(Long paramLong);

	public abstract List<GlobalType> getByParentId(Long paramLong);

	public abstract List<GlobalType> getByPath(String paramString);

	public abstract GlobalType findByTypeName(String paramString);
}
