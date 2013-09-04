package com.palmelf.eoffice.action.communicate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.communicate.SmsHistory;
import com.palmelf.eoffice.model.communicate.SmsMobile;
import com.palmelf.eoffice.service.communicate.SmsHistoryService;
import com.palmelf.eoffice.service.communicate.SmsMobileService;

public class SmsHistoryAction extends BaseAction {

	@Resource
	private SmsHistoryService smsHistoryService;

	@Resource
	private SmsMobileService smsMobileService;
	private SmsHistory smsHistory;
	private Long smsId;

	public Long getSmsId() {
		return this.smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public SmsHistory getSmsHistory() {
		return this.smsHistory;
	}

	public void setSmsHistory(SmsHistory smsHistory) {
		this.smsHistory = smsHistory;
	}

	@Override
	public String list() {
		String status = this.getRequest().getParameter("status");
		List list = null;
		QueryFilter filter = new QueryFilter(this.getRequest());
		if ((StringUtils.isNotEmpty(status)) && (status.equals(SmsMobile.STATUS_NOT_SENDED.toString()))) {
			list = this.smsMobileService.getAll(filter);
		} else {
			list = this.smsHistoryService.getAll(filter);
		}

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		buff.append(gson.toJson(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.smsHistoryService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		SmsHistory smsHistory = this.smsHistoryService.get(this.smsId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(smsHistory));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.smsHistoryService.save(this.smsHistory);
		this.setJsonString("{success:true}");
		return "success";
	}
}