package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.GlobalType;
import java.util.List;

public abstract interface GlobalTypeService extends BaseService<GlobalType> {
	public abstract List<GlobalType> getByParentIdCatKey(Long paramLong,
			String paramString);

	public abstract Integer getCountsByParentId(Long paramLong);

	public abstract void mulDel(Long paramLong);
}
