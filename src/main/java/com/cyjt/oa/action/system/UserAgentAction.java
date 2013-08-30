package com.cyjt.oa.action.system;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.system.UserAgent;
import com.cyjt.oa.service.system.UserAgentService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class UserAgentAction extends BaseAction {

	@Resource
	private UserAgentService userAgentService;
	private UserAgent userAgent;
	private Long grantId;

	public Long getGrantId() {
		return this.grantId;
	}

	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}

	public UserAgent getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());

		List<UserAgent> list = this.userAgentService.getAll(filter);

		Type type = new TypeToken<List<UserAgent>>() {
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
				this.userAgentService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		UserAgent userAgent = this.userAgentService.get(this.grantId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(userAgent));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.userAgentService.save(this.userAgent);
		setJsonString("{success:true}");
		return "success";
	}
}
