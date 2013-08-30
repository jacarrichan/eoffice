package com.cyjt.oa.action.system;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.AppUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.Department;
import com.cyjt.oa.service.system.AppUserService;
import com.cyjt.oa.service.system.CompanyService;
import com.cyjt.oa.service.system.DepartmentService;

public class DepartmentAction extends BaseAction {
	private Department department;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private CompanyService companyService;

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String select() {
		String depId = this.getRequest().getParameter("depId");
		QueryFilter filter = new QueryFilter(this.getRequest());
		if ((StringUtils.isNotEmpty(depId)) && (!"0".equals(depId))) {
			this.department = (this.departmentService.get(new Long(depId)));
			filter.addFilter("Q_path_S_LFK", this.department.getPath());
		}

		filter.addSorted("path", "asc");
		List<Department> list = this.departmentService.getAll(filter);
		Type type = new TypeToken<List<Department>>() {
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

	@Override
	public String list() {
		String opt = this.getRequest().getParameter("opt");
		StringBuffer buff = new StringBuffer();
		if (StringUtils.isNotEmpty(opt)) {
			buff.append("[");
		} else {
			buff.append("[{id:'0',text:'" + AppUtil.getCompanyName()
					+ "',expanded:true,children:[");
		}

		List<Department> listParent = this.departmentService
				.findByParentId(new Long(0L));
		for (Department dep : listParent) {
			buff.append("{id:'" + dep.getDepId() + "',text:'"
					+ dep.getDepName() + "',");
			buff.append(this.findChild(dep.getDepId()));
		}
		if (!listParent.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		if (StringUtils.isNotEmpty(opt)) {
			buff.append("]");
		} else {
			buff.append("]}]");
		}
		this.setJsonString(buff.toString());
		return "success";
	}

	public String findChild(Long depId) {
		StringBuffer buff1 = new StringBuffer("");
		List<Department> list = this.departmentService.findByParentId(depId);
		if (list.size() == 0) {
			buff1.append("leaf:true},");
			return buff1.toString();
		}
		buff1.append("expanded:true,children:[");
		for (Department dep2 : list) {
			buff1.append("{id:'" + dep2.getDepId() + "',text:'"
					+ dep2.getDepName() + "',");
			buff1.append(this.findChild(dep2.getDepId()));
		}
		buff1.deleteCharAt(buff1.length() - 1);
		buff1.append("]},");
		return buff1.toString();
	}

	public String add() {
		Long parentId = this.department.getParentId();
		String depPath = "";
		int level = 0;
		if (parentId.longValue() < 1L) {
			parentId = new Long(0L);
			depPath = "0.";
		} else {
			depPath = (this.departmentService.get(parentId)).getPath();
			level = (this.departmentService.get(parentId)).getDepLevel()
					.intValue();
		}
		if (level < 1) {
			level = 1;
		}
		this.department.setDepLevel(Integer.valueOf(level + 1));
		this.departmentService.save(this.department);
		if (this.department != null) {
			depPath = depPath + this.department.getDepId().toString() + ".";
			this.department.setPath(depPath);
			this.departmentService.save(this.department);
			this.setJsonString("{success:true}");
		} else {
			this.setJsonString("{success:false}");
		}
		return "success";
	}

	public String remove() {
		PagingBean pb = this.getInitPagingBean();
		Long depId = Long.valueOf(Long.parseLong(this.getRequest()
				.getParameter("depId")));
		Department department = this.departmentService.get(depId);
		List userList = this.appUserService.findByDepartment(
				department.getPath(), pb);
		if (userList.size() > 0) {
			this.setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
			return "success";
		}
		this.departmentService.remove(depId);
		List<Department> list = this.departmentService.findByParentId(depId);
		for (Department dep : list) {
			List users = this.appUserService
					.findByDepartment(dep.getPath(), pb);
			if (users.size() > 0) {
				this.setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
				return "success";
			}
			this.departmentService.remove(dep.getDepId());
		}
		this.setJsonString("{success:true}");
		return "success";
	}

	public String detail() {
		Long depId = Long.valueOf(Long.parseLong(this.getRequest()
				.getParameter("depId")));
		this.setDepartment(this.departmentService.get(depId));
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(gson.toJson(this.department));
		sb.append("]}");
		this.setJsonString(sb.toString());
		return "success";
	}
}
