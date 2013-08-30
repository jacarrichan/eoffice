package com.cyjt.oa.action.system;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.IndexDisplay;
import com.cyjt.oa.model.system.PanelItem;
import com.cyjt.oa.service.system.IndexDisplayService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class IndexDisplayAction extends BaseAction {

	@Resource
	private IndexDisplayService indexDisplayService;
	private IndexDisplay indexDisplay;
	private Long indexId;

	public Long getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public IndexDisplay getIndexDisplay() {
		return this.indexDisplay;
	}

	public void setIndexDisplay(IndexDisplay indexDisplay) {
		this.indexDisplay = indexDisplay;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<IndexDisplay> list = this.indexDisplayService.getAll(filter);

		Type type = new TypeToken<List<IndexDisplay>>() {
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
				this.indexDisplayService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		IndexDisplay indexDisplay = this.indexDisplayService.get(this.indexId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(indexDisplay));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String items = getRequest().getParameter("items");
		Gson gson = new Gson();
		PanelItem[] panelItems = (PanelItem[]) gson.fromJson(items,
				new TypeToken<PanelItem[]>() {
				}.getType());
		AppUser user = ContextUtil.getCurrentUser();
		List<IndexDisplay> list = this.indexDisplayService.findByUser(user
				.getUserId());
		for (IndexDisplay id : list) {
			this.indexDisplayService.remove(id);
		}
		for (PanelItem item : panelItems) {
			IndexDisplay indexDisplay = new IndexDisplay();
			indexDisplay.setAppUser(user);
			indexDisplay.setPortalId(item.getPanelId());
			indexDisplay.setColNum(Integer.valueOf(item.getColumn()));
			indexDisplay.setRowNum(Integer.valueOf(item.getRow()));
			this.indexDisplayService.save(indexDisplay);
		}
		setJsonString("{success:true}");
		return "success";
	}
}
