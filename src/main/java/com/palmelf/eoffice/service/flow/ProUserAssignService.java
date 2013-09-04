package com.palmelf.eoffice.service.flow;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.flow.ProUserAssign;

import java.util.List;

public abstract interface ProUserAssignService extends
		BaseService<ProUserAssign> {
	public abstract List<ProUserAssign> getByDeployId(String paramString);

	public abstract ProUserAssign getByDeployIdActivityName(
			String paramString1, String paramString2);
}
