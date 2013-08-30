package com.cyjt.oa.action.mobile.flow;

import java.util.List;

import javax.annotation.Resource;

import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.service.flow.ProDefinitionService;

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
