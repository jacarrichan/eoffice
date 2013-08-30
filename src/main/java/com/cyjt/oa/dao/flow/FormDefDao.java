package com.cyjt.oa.dao.flow;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.flow.FormDef;
import java.util.List;

public abstract interface FormDefDao extends BaseDao<FormDef> {
	public abstract List<FormDef> getByDeployId(String paramString);

	public abstract FormDef getByDeployIdActivityName(String paramString1,
			String paramString2);
}
