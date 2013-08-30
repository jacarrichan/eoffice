package com.cyjt.oa.action.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.communicate.OutMailUserSeting;
import com.cyjt.oa.service.communicate.OutMailUserSetingService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class OutMailSetAction extends BaseAction {

	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	private OutMailUserSeting outMailUserSeting;
	private Long id;

	public OutMailUserSeting getOutMailUserSeting() {
		return this.outMailUserSeting;
	}

	public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
		this.outMailUserSeting = outMailUserSeting;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<OutMailUserSeting> list = this.outMailUserSetingService
				.getAll(filter);

		Type type = new TypeToken<List<OutMailUserSeting>>() {
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
				this.outMailUserSetingService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		OutMailUserSeting outMailUserSeting = this.outMailUserSetingService
				.get(this.id);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");

		if (outMailUserSeting != null) {
			sb.append(gson.toJson(outMailUserSeting));
		} else {
			outMailUserSeting = new OutMailUserSeting();
			outMailUserSeting.setUserId(ContextUtil.getCurrentUserId());
			outMailUserSeting.setUserName(ContextUtil.getCurrentUser()
					.getUsername());

			sb.append(gson.toJson(outMailUserSeting));
		}

		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (getId() == null) {
			this.outMailUserSeting.setUserId(ContextUtil.getCurrentUserId());
			System.out.println("~~~~" + ContextUtil.getCurrentUserId());
			this.outMailUserSetingService.save(this.outMailUserSeting);
		}
		setJsonString("{success:true}");
		return "success";
	}
}
