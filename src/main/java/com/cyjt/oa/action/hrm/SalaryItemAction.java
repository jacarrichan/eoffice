package com.cyjt.oa.action.hrm;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.hrm.SalaryItem;
import com.cyjt.oa.service.hrm.SalaryItemService;

public class SalaryItemAction extends BaseAction {

	@Resource
	private SalaryItemService salaryItemService;
	private SalaryItem salaryItem;
	private Long salaryItemId;

	public Long getSalaryItemId() {
		return this.salaryItemId;
	}

	public void setSalaryItemId(Long salaryItemId) {
		this.salaryItemId = salaryItemId;
	}

	public SalaryItem getSalaryItem() {
		return this.salaryItem;
	}

	public void setSalaryItem(SalaryItem salaryItem) {
		this.salaryItem = salaryItem;
	}

	@Override
	public String list() {
		PagingBean pb = this.getInitPagingBean();
		String ids = this.getRequest().getParameter("exclude");
		if (StringUtils.isNotEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);
		}

		List<SalaryItem> list = this.salaryItemService.getAllExcludeId(ids, pb);

		Type type = new TypeToken<List<SalaryItem>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(pb.getTotalItems()).append(
				",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String search() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<SalaryItem> list = this.salaryItemService.getAll(filter);

		Type type = new TypeToken<List<SalaryItem>>() {
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
				this.salaryItemService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		SalaryItem salaryItem = this.salaryItemService.get(this.salaryItemId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(salaryItem));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.salaryItemService.save(this.salaryItem);
		this.setJsonString("{success:true}");
		return "success";
	}
}
