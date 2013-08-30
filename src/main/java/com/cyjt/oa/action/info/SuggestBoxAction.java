package com.cyjt.oa.action.info;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.info.SuggestBox;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.SysConfig;
import com.cyjt.oa.service.info.SuggestBoxService;
import com.cyjt.oa.service.system.AppUserService;
import com.cyjt.oa.service.system.SysConfigService;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

public class SuggestBoxAction extends BaseAction {

	@Resource
	private SuggestBoxService suggestBoxService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource
	private AppUserService appUserService;
	private SuggestBox suggestBox;
	private Long boxId;

	public Long getBoxId() {
		return this.boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public SuggestBox getSuggestBox() {
		return this.suggestBox;
	}

	public void setSuggestBox(SuggestBox suggestBox) {
		this.suggestBox = suggestBox;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<SuggestBox> list = this.suggestBoxService.getAll(filter);

		Type type = new TypeToken<List<SuggestBox>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
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
				this.suggestBoxService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		SuggestBox suggestBox = this.suggestBoxService.get(this.boxId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(suggestBox));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.suggestBox.setCreatetime(new Date());

		this.suggestBox.setSenderIp(getRequest().getRemoteAddr());

		SysConfig suggestId = this.sysConfigService.findByKey("suggestId");
		AppUser suggestManager = this.appUserService.get(new Long(suggestId
				.getDataValue()));

		if (suggestManager != null) {
			this.suggestBox.setRecFullname(suggestManager.getFullname());
			this.suggestBox.setRecUid(suggestManager.getUserId());
		}

		this.suggestBoxService.save(this.suggestBox);
		setJsonString("{success:true}");
		return "success";
	}

	public String reply() {
		SuggestBox orgSuggest = this.suggestBoxService.get(this.suggestBox
				.getBoxId());
		AppUser curUser = this.appUserService.get(new Long(
				this.sysConfigService.findByKey("suggestId").getDataValue()));
		orgSuggest.setReplyId(curUser.getUserId());
		orgSuggest.setIsOpen(this.suggestBox.getIsOpen());
		orgSuggest.setReplyFullname(curUser.getFullname());
		orgSuggest.setReplyTime(new Date());
		orgSuggest.setStatus(SuggestBox.STATUS_AUDIT);
		orgSuggest.setReplyContent(this.suggestBox.getReplyContent());
		this.suggestBoxService.save(orgSuggest);
		setJsonString("{success:true}");
		return "success";
	}

	public String match() {
		SuggestBox orgSuggest = this.suggestBoxService.get(this.suggestBox
				.getBoxId());
		if (orgSuggest.getQueryPwd().equals(this.suggestBox.getQueryPwd()))
			setJsonString("{success:true}");
		else {
			setJsonString("{failure:true}");
		}
		return "success";
	}
}
