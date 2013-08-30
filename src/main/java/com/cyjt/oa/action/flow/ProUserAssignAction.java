package com.cyjt.oa.action.flow;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.jbpm.jpdl.Node;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.model.flow.ProUserAssign;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProDefinitionService;
import com.cyjt.oa.service.flow.ProUserAssignService;

public class ProUserAssignAction extends BaseAction {

	@Resource
	private ProUserAssignService proUserAssignService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private ProDefinitionService proDefinitionService;
	private ProUserAssign proUserAssign;
	private Long assignId;

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public Long getAssignId() {
		return this.assignId;
	}

	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}

	public ProUserAssign getProUserAssign() {
		return this.proUserAssign;
	}

	public void setProUserAssign(ProUserAssign proUserAssign) {
		this.proUserAssign = proUserAssign;
	}

	@Override
	public String list() {
		Gson gson = new Gson();

		String defId = this.getRequest().getParameter("defId");

		ProDefinition proDefinition = this.proDefinitionService.get(new Long(
				defId));

		List<Node> nodes = this.jbpmService
				.getTaskNodesByDefId(new Long(defId));

		List<ProUserAssign> nodesAssignList = this.proUserAssignService
				.getByDeployId(proDefinition.getDeployId());

		StringBuffer buff = new StringBuffer("{result:[");

		for (int i = 0; i < nodes.size(); i++) {
			String nodeName = (nodes.get(i)).getName();
			buff.append("{activityName:'").append(nodeName)
					.append("',deployId:'" + proDefinition.getDeployId())
					.append("'");
			for (int j = 0; j < nodesAssignList.size(); j++) {
				ProUserAssign assign = nodesAssignList.get(j);
				if (!nodeName.equals(assign.getActivityName())) {
					continue;
				}
				buff.append(",assignId:'")
						.append(gson.toJson(assign.getAssignId()).replace("\"",
								""))
						.append("',userId:'")
						.append(assign.getUserId() == null ? "" : assign
								.getUserId())
						.append("',username:'")
						.append(gson.toJson(assign.getUsername()).replace("\"",
								""))
						.append("',roleId:'")
						.append(assign.getRoleId() == null ? "" : assign
								.getRoleId())
						.append("',roleName:'")
						.append(gson.toJson(assign.getRoleName()).replace("\"",
								"")).append("'");
				break;
			}

			buff.append("},");
		}

		if (!nodes.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}

		buff.append("]}");

		this.setJsonString(buff.toString());

		return "success";
	}

	public String get() {
		ProUserAssign proUserAssign = this.proUserAssignService
				.get(this.assignId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proUserAssign));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String data = this.getRequest().getParameter("data");

		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			ProUserAssign[] assigns = (ProUserAssign[]) gson.fromJson(data,
					new TypeToken<ProUserAssign[]>() {
					}.getType());
			for (ProUserAssign assign : assigns) {
				if (assign.getAssignId().longValue() == -1L) {
					assign.setAssignId(null);
				}
				this.proUserAssignService.save(assign);
			}
		}

		return "success";
	}
}
