package com.cyjt.oa.action.info;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.info.AppTips;
import com.cyjt.oa.service.info.AppTipsService;

public class AppTipsAction extends BaseAction {

	@Resource
	private AppTipsService appTipsService;
	private AppTips appTips;
	private Long tipsId;

	public Long getTipsId() {
		return this.tipsId;
	}

	public void setTipsId(Long tipsId) {
		this.tipsId = tipsId;
	}

	public AppTips getAppTips() {
		return this.appTips;
	}

	public void setAppTips(AppTips appTips) {
		this.appTips = appTips;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		List<AppTips> list = this.appTipsService.getAll(filter);

		Type type = new TypeToken<List<AppTips>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		AppTips tips;
		if (this.getRequest().getParameter("ids").equals("all")) {
			QueryFilter filter = new QueryFilter(this.getRequest());
			filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
					.getCurrentUserId().toString());
			List list = this.appTipsService.getAll(filter);
			for (Iterator localIterator = list.iterator(); localIterator
					.hasNext();) {
				tips = (AppTips) localIterator.next();
				this.appTipsService.remove(tips);
			}
		} else {
			String[] ids = this.getRequest().getParameterValues("ids");
			if (ids != null) {
				for (String id : ids) {
					this.appTipsService.remove(new Long(id));
				}
			}
		}
		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		AppTips appTips = this.appTipsService.get(this.tipsId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(appTips));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String data = this.getRequest().getParameter("data");
		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			AppTips[] tips = (AppTips[]) gson.fromJson(data,
					new TypeToken<AppTips[]>() {
					}.getType());
			for (AppTips tip : tips) {
				if (tip.getTipsId().longValue() == -1L) {
					tip.setTipsId(null);
					SimpleDateFormat date = new SimpleDateFormat(
							"yyMMddHHmmssSSS");
					String customerNo = date.format(new Date());
					tip.setTipsName("tips" + customerNo);
					tip.setCreateTime(new Date());
				}
				tip.setAppUser(ContextUtil.getCurrentUser());
				this.appTipsService.save(tip);
			}
		}

		this.setJsonString("{success:true}");
		return "success";
	}
}
