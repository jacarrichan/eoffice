package com.palmelf.eoffice.service.flow;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.flow.FormDef;

import java.util.List;

public abstract interface FormDefService extends BaseService<FormDef> {
	public abstract List<FormDef> getByDeployId(String paramString);

	public abstract FormDef getByDeployIdActivityName(String paramString1,
			String paramString2);
}
