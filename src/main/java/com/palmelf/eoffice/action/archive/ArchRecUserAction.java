package com.palmelf.eoffice.action.archive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.archive.ArchRecUser;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.Department;
import com.palmelf.eoffice.service.archive.ArchRecUserService;
import com.palmelf.eoffice.service.system.AppUserService;
import com.palmelf.eoffice.service.system.DepartmentService;

import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class ArchRecUserAction extends BaseAction {

	@Resource
	private ArchRecUserService archRecUserService;
	private ArchRecUser archRecUser;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private AppUserService appUserService;
	private Long archRecId;

	public Long getArchRecId() {
		return this.archRecId;
	}

	public void setArchRecId(Long archRecId) {
		this.archRecId = archRecId;
	}

	public ArchRecUser getArchRecUser() {
		return this.archRecUser;
	}

	public void setArchRecUser(ArchRecUser archRecUser) {
		this.archRecUser = archRecUser;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ArchRecUser> list = this.archRecUserService.getAll(filter);

		Type type = new TypeToken<List<ArchRecUser>>() {
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

	public String depList() {
		List list = this.archRecUserService.findDepAll();
		StringBuffer sb = new StringBuffer("{success:true,'totalCounts':");
		sb.append(list.size()).append(",result:[");
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				sb.append(",");
			}
			ArchRecUser ar = (ArchRecUser) ((Object[]) list.get(i))[0];
			Department dep = (Department) ((Object[]) list.get(i))[1];
			sb.append("{'depId':'" + dep.getDepId() + "','depName':'"
					+ dep.getDepName() + "','depLevel':" + dep.getDepLevel()
					+ ",");
			if (ar != null)
				sb.append("'archRecId':'" + ar.getArchRecId() + "','userId':'"
						+ ar.getUserId() + "','fullname':'" + ar.getFullname()
						+ "'}");
			else {
				sb.append("'archRecId':'','userId':'','fullname':''}");
			}
		}
		sb.append("]}");
		this.jsonString = sb.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archRecUserService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchRecUser archRecUser = this.archRecUserService.get(this.archRecId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archRecUser));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String data = getRequest().getParameter("data");
		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			ArchRecUser[] aru = (ArchRecUser[]) gson.fromJson(data,
					new TypeToken<ArchRecUser[]>() {
					}.getType());
			for (ArchRecUser archRecUser : aru) {
				if (archRecUser.getArchRecId().longValue() == -1L) {
					archRecUser.setArchRecId(null);
				}
				if (archRecUser.getDepId() != null) {
					Department department = this.departmentService
							.get(archRecUser.getDepId());
					archRecUser.setDepartment(department);
					this.archRecUserService.save(archRecUser);
				} else {
					setJsonString("{success:false}");
				}
			}
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String select() {
		String strDepId = getRequest().getParameter("depId");
		StringBuffer sb = new StringBuffer("[");
		if (StringUtils.isNotEmpty(strDepId)) {
			List<AppUser> list = this.appUserService.findByDepId(new Long(
					strDepId));
			for (AppUser appUser : list) {
				sb.append("['").append(appUser.getUserId()).append("','")
						.append(appUser.getFullname()).append("'],");
			}
			if (list.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		sb.append("]");
		setJsonString(sb.toString());
		return "success";
	}
}
