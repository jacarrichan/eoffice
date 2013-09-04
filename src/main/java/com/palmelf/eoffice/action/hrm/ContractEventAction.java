package com.palmelf.eoffice.action.hrm;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.hrm.ContractEvent;
import com.palmelf.eoffice.service.hrm.ContractEventService;

public class ContractEventAction extends BaseAction {

	@Resource
	private ContractEventService contractEventService;

	private ContractEvent contractEvent;

	private Long eventId;

	public Long getEventId() {
		return this.eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public ContractEvent getContractEvent() {
		return this.contractEvent;
	}

	public void setContractEvent(ContractEvent contractEvent) {
		this.contractEvent = contractEvent;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<ContractEvent> list = this.contractEventService.getAll(filter);

		Type type = new TypeToken<List<ContractEvent>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.contractEventService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ContractEvent contractEvent = this.contractEventService.get(this.eventId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(contractEvent));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.contractEvent.getEventId() == null) {
			this.contractEvent.setCreateTime(new Date());
			this.contractEvent.setCreator(ContextUtil.getCurrentUser().getFullname());
			this.contractEventService.save(this.contractEvent);
		} else {
			this.contractEvent.setCreateTime(new Date());
			this.contractEvent.setCreator(ContextUtil.getCurrentUser().getFullname());
			this.contractEventService.save(this.contractEvent);
		}
		this.setJsonString("{success:true}");
		return "success";
	}
}
