package com.palmelf.eoffice.action.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.system.AppFunction;
import com.palmelf.eoffice.service.system.AppFunctionService;

import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class AppFunctionAction extends BaseAction {

	@Resource
	private AppFunctionService appFunctionService;
	private AppFunction appFunction;
	private Long functionId;

	public Long getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	public AppFunction getAppFunction() {
		return this.appFunction;
	}

	public void setAppFunction(AppFunction appFunction) {
		this.appFunction = appFunction;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<AppFunction> list = this.appFunctionService.getAll(filter);

		Type type = new TypeToken<List<AppFunction>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.appFunctionService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		AppFunction appFunction = this.appFunctionService.get(this.functionId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(appFunction));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.appFunctionService.save(this.appFunction);
		setJsonString("{success:true}");
		return "success";
	}
}
