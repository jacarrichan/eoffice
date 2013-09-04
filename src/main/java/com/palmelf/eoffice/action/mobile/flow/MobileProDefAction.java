package com.palmelf.eoffice.action.mobile.flow;

import java.util.List;

import javax.annotation.Resource;

import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.flow.ProDefinition;
import com.palmelf.eoffice.service.flow.ProDefinitionService;

public class MobileProDefAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Override
	public String list() {
		List<ProDefinition> proDefList = this.proDefinitionService.getAll(this.getInitPagingBean());
		this.getRequest().setAttribute("proDefList", proDefList);
		return "success";
	}
}
