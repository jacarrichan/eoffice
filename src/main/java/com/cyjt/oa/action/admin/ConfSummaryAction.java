package com.cyjt.oa.action.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.ConfSummary;
import com.cyjt.oa.model.admin.Conference;
import com.cyjt.oa.service.admin.ConfSummaryService;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

public class ConfSummaryAction extends BaseAction {

	@Resource
	private ConfSummaryService confSummaryService;
	private ConfSummary confSummary;
	private Long sumId;
	private Date endtime;
	private String fileIds;

	public String getFileIds() {
		return this.fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Long getSumId() {
		return this.sumId;
	}

	public void setSumId(Long sumId) {
		this.sumId = sumId;
	}

	public ConfSummary getConfSummary() {
		return this.confSummary;
	}

	public void setConfSummary(ConfSummary confSummary) {
		this.confSummary = confSummary;
	}

	@Override
	public String list() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		QueryFilter filter = new QueryFilter(getRequest());
		if (this.endtime != null)
			filter.addFilter("Q_createtime_D_LE", sdf.format(this.endtime));
		List<ConfSummary> list = this.confSummaryService.getAll(filter);
		Type type = new TypeToken<List<ConfSummary>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
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
				this.confSummaryService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String del() {
		String id = getRequest().getParameter("sumId");
		this.confSummaryService.remove(new Long(id));
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		ConfSummary confSummary = this.confSummaryService.get(this.sumId);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(confSummary));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String send() {
		String content = this.confSummary.getSumContent();
		if ((content == null) || (content.isEmpty()) || (content.equals(" "))) {
			setJsonString("{failure:true,msg:'读不起，会议纪要内容不能为空，请输入！'}");
		} else {
			this.confSummary.setCreatetime(new Date());
			this.confSummary.setCreator(ContextUtil.getCurrentUser()
					.getUsername());
			this.confSummary.setStatus((short) 1);
			this.confSummaryService.send(this.confSummary, this.fileIds);
			setJsonString("{success:true}");
		}
		return "success";
	}

	@Override
	public String save() {
		String content = this.confSummary.getSumContent();
		if ((content == null) || (content.isEmpty()) || (content.equals(" "))) {
			setJsonString("{failure:true,msg:'对不起，会议纪要内容不能为空，请重新输入！'}");
		} else {
			this.confSummary.setCreatetime(new Date());
			this.confSummary.setCreator(ContextUtil.getCurrentUser()
					.getUsername());
			this.confSummary.setStatus((short) 0);
			this.confSummaryService.save(this.confSummary, this.fileIds);
			setJsonString("{success:true}");
		}
		return "success";
	}

	@Override
	public String edit() {
		this.confSummaryService.save(this.confSummary, this.fileIds);
		setJsonString("{success:true}");
		return "success";
	}

	public String display() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_status_SN_EQ", "1");
		filter.addSorted("createtime", "DESC");
		List<ConfSummary> list = this.confSummaryService.getAll(filter);
		for (int i = 0; i < list.size(); i++) {
			ConfSummary cm = list.get(i);
			Conference cf = cm.getConfId();
			if ((cm.getStatus().shortValue() != 1)
					&& (myConfSummary(cf.getCompere()))
					&& (myConfSummary(cf.getRecorder()))
					&& (myConfSummary(cf.getAttendUsers()))) {
				list.remove(i);
			}
			if (i > 7) {
				for (int j = 8; j < list.size(); j++)
					list.remove(j);
			}
		}
		getRequest().setAttribute("confSummaryList", list);
		return "display";
	}

	private boolean myConfSummary(String str) {
		boolean bo = true;
		Long userId = ContextUtil.getCurrentUserId();
		int index = str.indexOf(userId.toString());
		if ((index > 1) && (str.substring(index - 1, index).equals(",")))
			bo = false;
		else if (index == 0)
			bo = false;
		return bo;
	}
}
