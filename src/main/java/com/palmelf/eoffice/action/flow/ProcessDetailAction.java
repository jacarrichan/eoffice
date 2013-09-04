package com.palmelf.eoffice.action.flow;

import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.flow.ProDefinition;
import com.palmelf.eoffice.service.flow.ProDefinitionService;

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
