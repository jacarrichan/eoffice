package com.cyjt.oa.service.flow;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.flow.FormDef;
import java.util.List;

public abstract interface FormDefService extends BaseService<FormDef> {
	public abstract List<FormDef> getByDeployId(String paramString);

	public abstract FormDef getByDeployIdActivityName(String paramString1,
			String paramString2);
}
