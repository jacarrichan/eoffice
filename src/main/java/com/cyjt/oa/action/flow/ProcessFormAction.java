package com.cyjt.oa.action.flow;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.oa.service.flow.ProcessFormService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class ProcessFormAction extends BaseAction {

	@Resource
	private ProcessFormService processFormService;
	private ProcessForm processForm;
	private Long formId;

	public Long getFormId() {
		return this.formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public ProcessForm getProcessForm() {
		return this.processForm;
	}

	public void setProcessForm(ProcessForm processForm) {
		this.processForm = processForm;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ProcessForm> list = this.processFormService.getAll(filter);

		Type type = new TypeToken<List<ProcessForm>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.processFormService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ProcessForm processForm = this.processFormService.get(this.formId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(processForm));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.processFormService.save(this.processForm);
		setJsonString("{success:true}");
		return "success";
	}
}
