package com.palmelf.eoffice.action.customer;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.util.JsonUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.customer.CusConnection;
import com.palmelf.eoffice.service.customer.CusConnectionService;

import flexjson.JSONSerializer;
import java.util.List;
import javax.annotation.Resource;

public class CusConnectionAction extends BaseAction {

	@Resource
	private CusConnectionService cusConnectionService;
	private CusConnection cusConnection;
	private Long connId;

	public Long getConnId() {
		return this.connId;
	}

	public void setConnId(Long connId) {
		this.connId = connId;
	}

	public CusConnection getCusConnection() {
		return this.cusConnection;
	}

	public void setCusConnection(CusConnection cusConnection) {
		this.cusConnection = cusConnection;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.cusConnectionService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = JsonUtil.getJSONSerializer(new String[] {
				"startDate", "endDate" });
		buff.append(json.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.cusConnectionService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		CusConnection cusConnection = this.cusConnectionService
				.get(this.connId);

		JSONSerializer json = JsonUtil.getJSONSerializer(new String[] {
				"startDate", "endDate" });

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(json.exclude(new String[] { "class" }).serialize(
				cusConnection));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		if (this.cusConnection.getStartDate().getTime() < this.cusConnection
				.getEndDate().getTime())
			pass = true;
		else
			buff.append("msg:'交往结束日期不能早于开始日期!',");

		if (pass) {
			this.cusConnection.setCreator(ContextUtil.getCurrentUser()
					.getFullname());
			this.cusConnectionService.save(this.cusConnection);
			buff.append("success:true}");
		} else {
			buff.append("failure:true}");
		}
		setJsonString(buff.toString());
		return "success";
	}
}
