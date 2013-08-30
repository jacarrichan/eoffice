package com.cyjt.oa.action.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.Regulation;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.Department;
import com.cyjt.oa.model.system.FileAttach;
import com.cyjt.oa.model.system.GlobalType;
import com.cyjt.oa.service.admin.RegulationService;
import com.cyjt.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;

public class RegulationAction extends BaseAction {

	@Resource
	private RegulationService regulationService;

	@Resource
	private FileAttachService fileAttachService;
	private Regulation regulation;
	private Long regId;

	public Long getRegId() {
		return this.regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public Regulation getRegulation() {
		return this.regulation;
	}

	public void setRegulation(Regulation regulation) {
		this.regulation = regulation;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<Regulation> list = this.regulationService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "issueDate" });
		buff.append(json.exclude(new String[] { "content" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String scan() {
		AppUser curUser = ContextUtil.getCurrentUser();
		Department dep = curUser.getDepartment();

		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.setFilterName("GetRegulationWithRights");

		filter.addParamValue(Regulation.STATUS_EFFECT);
		if (dep != null) {
			filter.addParamValue("%," + curUser.getDepartment().getDepId() + ",%");
		} else {
			filter.addParamValue("%,0,%");
		}

		filter.addParamValue("%," + ContextUtil.getCurrentUserId() + ",%");

		List<Regulation> list = this.regulationService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "issueDate" });
		buff.append(json.exclude(new String[] { "content" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.regulationService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Regulation regulation = this.regulationService.get(this.regId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(regulation));

		GlobalType proType = regulation.getGlobalType();
		if (proType != null) {
			sb.deleteCharAt(sb.length() - 1);
			sb.append(",proTypeId:").append(proType.getProTypeId()).append(",proTypeName:'")
					.append(proType.getTypeName()).append("'}");
		}

		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String fileIds = this.getRequest().getParameter("regAttachsFileIds");

		Set<FileAttach> regAttachs = new HashSet<FileAttach>();

		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fIds = fileIds.split(",");
			for (String fId : fIds) {
				FileAttach fileAttach = this.fileAttachService.get(new Long(fId));
				regAttachs.add(fileAttach);
			}

		}

		String depIds = this.regulation.getRecDepIds();
		if (StringUtils.isNotEmpty(depIds)) {
			String[] dIds = depIds.split(",");
			StringBuffer newDepIds = new StringBuffer(",");
			for (String did : dIds) {
				newDepIds.append(did).append(",");
			}
			this.regulation.setRecDepIds(newDepIds.toString());
		}

		String userIds = this.regulation.getRecUserIds();
		if (StringUtils.isNotEmpty(userIds)) {
			String[] uIds = userIds.split(",");
			StringBuffer newUserIds = new StringBuffer(",");
			for (String uid : uIds) {
				newUserIds.append(uid).append(",");
			}
			this.regulation.setRecUserIds(newUserIds.toString());
		}

		if (this.regulation.getRegId() == null) {
			this.regulation.setRegAttachs(regAttachs);
			this.regulationService.save(this.regulation);
		} else {
			Regulation orgRegulation = this.regulationService.get(this.regulation.getRegId());
			try {
				BeanUtil.copyNotNullProperties(orgRegulation, this.regulation);
				orgRegulation.setRegAttachs(regAttachs);
				this.regulationService.save(orgRegulation);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		}
		this.setJsonString("{success:true}");
		return "success";
	}
}
