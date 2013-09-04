package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.GlobalType;

import java.util.List;

public abstract interface GlobalTypeService extends BaseService<GlobalType> {
	public abstract List<GlobalType> getByParentIdCatKey(Long paramLong,
			String paramString);

	public abstract Integer getCountsByParentId(Long paramLong);

	public abstract void mulDel(Long paramLong);
}
