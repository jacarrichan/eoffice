package com.cyjt.oa.action.hrm;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.hrm.SalaryPayoff;
import com.cyjt.oa.model.hrm.StandSalaryItem;
import com.cyjt.oa.service.hrm.SalaryPayoffService;
import com.cyjt.oa.service.hrm.StandSalaryItemService;

public class SalaryPayoffAction extends BaseAction {

	@Resource
	private SalaryPayoffService salaryPayoffService;

	@Resource
	private StandSalaryItemService standSalaryItemService;
	private SalaryPayoff salaryPayoff;
	private Long recordId;

	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public SalaryPayoff getSalaryPayoff() {
		return this.salaryPayoff;
	}

	public void setSalaryPayoff(SalaryPayoff salaryPayoff) {
		this.salaryPayoff = salaryPayoff;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<SalaryPayoff> list = this.salaryPayoffService.getAll(filter);

		Type type = new TypeToken<List<SalaryPayoff>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.salaryPayoffService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		SalaryPayoff salaryPayoff = this.salaryPayoffService.get(this.recordId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(gson.toJson(salaryPayoff));
		sb.append("]}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.salaryPayoff.getRecordId() == null) {
			this.salaryPayoff.setCheckStatus(Short.valueOf(SalaryPayoff.CHECK_FLAG_NONE));
			this.salaryPayoff.setRegTime(new Date());
			this.salaryPayoff.setRegister(ContextUtil.getCurrentUser().getFullname());
		}
		BigDecimal acutalAmount = this.salaryPayoff.getStandAmount().add(this.salaryPayoff.getEncourageAmount())
				.subtract(this.salaryPayoff.getDeductAmount());
		if (this.salaryPayoff.getAchieveAmount().compareTo(new BigDecimal(0)) == 1) {
			acutalAmount = acutalAmount.add(this.salaryPayoff.getAchieveAmount());
		}
		this.salaryPayoff.setAcutalAmount(acutalAmount);
		this.salaryPayoffService.save(this.salaryPayoff);
		this.setJsonString("{success:true}");
		return "success";
	}

	public String check() {
		SalaryPayoff checkSalaryPayoff = this.salaryPayoffService.get(new Long(this.recordId.longValue()));
		checkSalaryPayoff.setCheckTime(new Date());
		checkSalaryPayoff.setCheckName(ContextUtil.getCurrentUser().getFullname());
		checkSalaryPayoff.setCheckStatus(this.salaryPayoff.getCheckStatus());
		checkSalaryPayoff.setCheckOpinion(this.salaryPayoff.getCheckOpinion());
		this.salaryPayoffService.save(checkSalaryPayoff);
		return "success";
	}

	public String personal() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<SalaryPayoff> list = this.salaryPayoffService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:[");
		for (SalaryPayoff salaryDetail : list) {
			buff.append("{recordId:'").append(salaryDetail.getRecordId()).append("',fullname:'")
					.append(salaryDetail.getFullname()).append("',profileNo:'").append(salaryDetail.getProfileNo())
					.append("',idNo:'").append(salaryDetail.getIdNo()).append("',standAmount:'")
					.append(salaryDetail.getStandAmount()).append("',acutalAmount:'")
					.append(salaryDetail.getAcutalAmount()).append("',startTime:'").append(salaryDetail.getStartTime())
					.append("',endTime:'").append(salaryDetail.getEndTime()).append("',checkStatus:'")
					.append(salaryDetail.getCheckStatus());
			List<StandSalaryItem> items = this.standSalaryItemService.getAllByStandardId(salaryDetail.getStandardId());
			StringBuffer content = new StringBuffer(
					"<table class=\"table-info\" cellpadding=\"0\" cellspacing=\"1\" width=\"98%\" align=\"center\"><tr>");

			if ((salaryDetail.getEncourageAmount() != new BigDecimal(0)) && (salaryDetail.getEncourageAmount() != null)) {
				content.append("<th>").append("奖励金额</th><td>").append(salaryDetail.getEncourageAmount())
						.append("</td>");
			}

			if ((salaryDetail.getEncourageAmount() != new BigDecimal(0)) && (salaryDetail.getEncourageAmount() != null)) {
				content.append("<th>").append("扣除金额</th><td>").append(salaryDetail.getDeductAmount()).append("</td>");
			}

			if ((salaryDetail.getEncourageAmount() != new BigDecimal(0)) && (salaryDetail.getEncourageAmount() != null)) {
				content.append("<th>").append("效绩金额</th><td>").append(salaryDetail.getAchieveAmount()).append("</td>");
			}
			content.append("</tr></table><table class=\"table-info\" cellpadding=\"0\" cellspacing=\"1\" width=\"98%\" align=\"center\"><tr>");
			for (StandSalaryItem item : items) {
				content.append("<th>").append(item.getItemName()).append("</th>");
			}
			content.append("</tr><tr>");
			for (StandSalaryItem item2 : items) {
				content.append("<td>").append(item2.getAmount()).append("</td>");
			}
			content.append("</tr></table>");
			buff.append("',content:'").append(content.toString()).append("'},");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");

		this.jsonString = buff.toString();
		return "success";
	}
}
