package com.palmelf.eoffice.dao.flow;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.flow.FormDef;

import java.util.List;

public abstract interface FormDefDao extends BaseDao<FormDef> {
	public abstract List<FormDef> getByDeployId(String paramString);

	public abstract FormDef getByDeployIdActivityName(String paramString1,
			String paramString2);
}
