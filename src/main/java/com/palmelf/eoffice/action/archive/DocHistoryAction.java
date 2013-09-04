package com.palmelf.eoffice.action.archive;

import com.google.gson.Gson;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.JsonUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.archive.DocHistory;
import com.palmelf.eoffice.service.archive.DocHistoryService;

import flexjson.JSONSerializer;
import java.util.List;
import javax.annotation.Resource;

public class DocHistoryAction extends BaseAction {

	@Resource
	private DocHistoryService docHistoryService;
	private DocHistory docHistory;
	private Long historyId;

	public Long getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public DocHistory getDocHistory() {
		return this.docHistory;
	}

	public void setDocHistory(DocHistory docHistory) {
		this.docHistory = docHistory;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.docHistoryService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = JsonUtil
				.getJSONSerializer(new String[] { "updatetime" });
		buff.append(json.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.docHistoryService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		DocHistory docHistory = this.docHistoryService.get(this.historyId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(docHistory));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.docHistoryService.save(this.docHistory);
		setJsonString("{success:true}");
		return "success";
	}
}
