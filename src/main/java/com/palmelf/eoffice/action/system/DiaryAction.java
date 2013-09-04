package com.palmelf.eoffice.action.system;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.Diary;
import com.palmelf.eoffice.service.system.DiaryService;
import com.palmelf.eoffice.service.system.UserSubService;


import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class DiaryAction extends BaseAction {

	@Resource
	private DiaryService diaryService;

	@Resource
	private UserSubService userSubService;
	private Diary diary;
	private Date from;
	private Date to;
	private Long diaryId;

	public Date getFrom() {
		return this.from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return this.to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Long getDiaryId() {
		return this.diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public Diary getDiary() {
		return this.diary;
	}

	public void setDiary(Diary diary) {
		this.diary = diary;
	}

	@Override
	public String list() {
		AppUser user = ContextUtil.getCurrentUser();
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", user.getId().toString());
		List<Diary> list = this.diaryService.getAll(filter);
		Type type = new TypeToken<List<Diary>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
				.excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String subUser() {
		PagingBean pb = this.getInitPagingBean();
		String usrIds = this.getRequest().getParameter("userId");
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(usrIds)) {
			sb.append(usrIds);
		} else {
			List<Long> list = this.userSubService.subUsers(ContextUtil
					.getCurrentUserId());
			for (Long l : list) {
				sb.append(l.toString()).append(",");
			}
			if (list.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		List<Diary> diaryList = new ArrayList<Diary>();

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':");

		if (sb.length() > 0) {
			diaryList = this.diaryService.getSubDiary(sb.toString(), pb);
			buff.append(pb.getTotalItems());
		} else {
			buff.append("0");
		}
		buff.append(",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "dayTime" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(
				diaryList));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String search() {
		AppUser user = ContextUtil.getCurrentUser();
		QueryFilter filter = new QueryFilter(this.getRequest());

		filter.addFilter("Q_appUser.userId_L_EQ", user.getId().toString());

		if (this.getRequest().getParameter("from") != "") {
			filter.addFilter("Q_dayTime_D_GE",
					this.getRequest().getParameter("from"));
		}

		if (this.getRequest().getParameter("to") != "") {
			filter.addFilter("Q_dayTime_D_LE",
					this.getRequest().getParameter("to"));
		}

		filter.addFilter("Q_content_S_LK", this.diary.getContent());

		if (this.diary.getDiaryType() != null) {
			filter.addFilter("Q_diaryType_SN_EQ", this.diary.getDiaryType()
					.toString());
		}

		List<Diary> list = this.diaryService.getAll(filter);
		Type type = new TypeToken<List<Diary>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.diaryService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		Diary diary = this.diaryService.get(this.diaryId);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(diary));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		AppUser user = ContextUtil.getCurrentUser();
		this.diary.setAppUser(user);
		this.diaryService.save(this.diary);
		this.setJsonString("{success:true}");
		return "success";
	}

	public String check() {
		String strId = this.getRequest().getParameter("diaryId");
		if (StringUtils.isNotEmpty(strId)) {
			this.diary = (this.diaryService.get(new Long(strId)));
		}
		return "check";
	}

	public String display() {
		AppUser user = ContextUtil.getCurrentUser();
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", user.getId().toString());
		filter.addSorted("diaryId", "desc");
		List<Diary> list = this.diaryService.getAll(filter);
		this.getRequest().setAttribute("diaryList", list);
		return "display";
	}
}
