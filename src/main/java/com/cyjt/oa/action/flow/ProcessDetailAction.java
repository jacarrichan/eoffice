package com.cyjt.oa.action.flow;

import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.service.flow.ProDefinitionService;
import javax.annotation.Resource;

public class ProcessDetailAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;
	private ProDefinition proDefinition;

	public ProDefinition getProDefinition() {
		return this.proDefinition;
	}

	public void setProDefinition(ProDefinition proDefinition) {
		this.proDefinition = proDefinition;
	}

	@Override
	public String execute() throws Exception {
		String defId = getRequest().getParameter("defId");
		this.proDefinition = (this.proDefinitionService.get(new Long(defId)));
		return "success";
	}
}
