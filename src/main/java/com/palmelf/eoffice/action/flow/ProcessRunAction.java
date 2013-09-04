package com.palmelf.eoffice.action.flow;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.flow.ProcessRun;
import com.palmelf.eoffice.service.flow.ProcessRunService;

public class ProcessRunAction extends BaseAction {

	@Resource
	private ProcessRunService processRunService;
	private ProcessRun processRun;
	private Long runId;

	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public ProcessRun getProcessRun() {
		return this.processRun;
	}

	public void setProcessRun(ProcessRun processRun) {
		this.processRun = processRun;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());

		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());

		List<ProcessRun> list = this.processRunService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:[");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (ProcessRun run : list) {
			buff.append("{runId:'").append(run.getRunId())
					.append("',subject:'").append(run.getSubject())
					.append("',createtime:'")
					.append(sdf.format(run.getCreatetime())).append("',piId:'")
					.append(run.getPiId()).append("',defId:'")
					.append(run.getProDefinition().getDefId())
					.append("',runStatus:'").append(run.getRunStatus())
					.append("'},");
		}

		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String my() {
		QueryFilter filter = new QueryFilter(this.getRequest());

		filter.setFilterName("MyAttendProcessRun");

		filter.addParamValue(ContextUtil.getCurrentUserId());

		List<ProcessRun> processRunList = this.processRunService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:[");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (ProcessRun run : processRunList) {
			buff.append("{runId:'").append(run.getRunId())
					.append("',subject:'").append(run.getSubject())
					.append("',createtime:'")
					.append(sdf.format(run.getCreatetime())).append("',piId:'")
					.append(run.getPiId()).append("',defId:'")
					.append(run.getProDefinition().getDefId())
					.append("',runStatus:'").append(run.getRunStatus())
					.append("'},");
		}

		if (processRunList.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.processRunService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		ProcessRun processRun = this.processRunService.get(this.runId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(processRun));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.processRunService.save(this.processRun);
		this.setJsonString("{success:true}");
		return "success";
	}
}
