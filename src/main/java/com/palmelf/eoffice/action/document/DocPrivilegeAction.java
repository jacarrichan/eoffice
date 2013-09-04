package com.palmelf.eoffice.action.document;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.document.DocPrivilege;
import com.palmelf.eoffice.model.system.AppRole;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.Department;
import com.palmelf.eoffice.service.document.DocPrivilegeService;
import com.palmelf.eoffice.service.system.AppRoleService;
import com.palmelf.eoffice.service.system.AppUserService;
import com.palmelf.eoffice.service.system.DepartmentService;

public class DocPrivilegeAction extends BaseAction {

	@Resource
	private DocPrivilegeService docPrivilegeService;
	private DocPrivilege docPrivilege;

	@Resource
	private AppRoleService appRoleService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private DepartmentService departmentService;
	private Long privilegeId;

	public Long getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public DocPrivilege getDocPrivilege() {
		return this.docPrivilege;
	}

	public void setDocPrivilege(DocPrivilege docPrivilege) {
		this.docPrivilege = docPrivilege;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<DocPrivilege> list = this.docPrivilegeService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:[");
		for (DocPrivilege privilege : list) {
			Integer rights = privilege.getRights();
			String right = Integer.toBinaryString(rights.intValue());
			Integer read = null;
			Integer update = null;
			Integer delete = null;
			char[] cc = right.toCharArray();
			if ((cc.length == 1) && (cc[0] == '1')) {
				read = Integer.valueOf(1);
			}
			if (cc.length == 2) {
				if (cc[0] == '1') {
					update = Integer.valueOf(1);
				}
				if (cc[1] == '1') {
					read = Integer.valueOf(1);
				}
			}
			if (cc.length == 3) {
				if (cc[0] == '1') {
					delete = Integer.valueOf(1);
				}
				if (cc[1] == '1') {
					update = Integer.valueOf(1);
				}
				if (cc[2] == '1') {
					read = Integer.valueOf(1);
				}
			}
			buff.append("{'privilegeId':" + privilege.getPrivilegeId()
					+ ",'udrId':" + privilege.getUdrId() + ",'udrName':'"
					+ privilege.getUdrName() + "','folderName':'"
					+ privilege.getDocFolder().getFolderName() + "','flag':"
					+ privilege.getFlag() + ",'rightR':" + read + ",'rightU':"
					+ update + ",'rightD':" + delete + "},");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.docPrivilegeService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		DocPrivilege docPrivilege = this.docPrivilegeService
				.get(this.privilegeId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(docPrivilege));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String change() {
		String strPrivilegeId = this.getRequest().getParameter("privilegeId");
		String strField = this.getRequest().getParameter("field");
		String strFieldValue = this.getRequest().getParameter("fieldValue");
		if (StringUtils.isNotEmpty(strPrivilegeId)) {
			this.docPrivilege = (this.docPrivilegeService.get(Long.valueOf(Long
					.parseLong(strPrivilegeId))));
			Integer in = this.docPrivilege.getRights();
			if (in.intValue() > 0) {
				String str = Integer.toBinaryString(in.intValue());
				StringBuffer buff = new StringBuffer(str);
				if (buff.length() == 1) {
					buff.insert(0, "00");
				}
				if (buff.length() == 2) {
					buff.insert(0, "0");
				}
				if (buff.length() <= 0) {
					buff.insert(0, "000");
				}
				String rights = "";
				if ("rightR".equals(strField)) {
					StringBuffer newBuff = new StringBuffer();
					if ("true".equals(strFieldValue)) {
						newBuff.append(buff.deleteCharAt(2).toString()).append(
								"1");
					} else {
						newBuff.append(buff.deleteCharAt(2).toString()).append(
								"0");
					}
					rights = newBuff.toString();
				}

				if ("rightU".equals(strField)) {
					StringBuffer newBuff = new StringBuffer();
					if ("true".equals(strFieldValue)) {
						newBuff.append(buff.charAt(0)).append("1")
								.append(buff.charAt(2));
					} else {
						newBuff.append(buff.charAt(0)).append("0")
								.append(buff.charAt(2));
					}
					rights = newBuff.toString();
				}

				if ("rightD".equals(strField)) {
					StringBuffer newBuff = new StringBuffer();
					if ("true".equals(strFieldValue)) {
						newBuff.append("1").append(
								buff.deleteCharAt(0).toString());
					} else {
						newBuff.append("0").append(
								buff.deleteCharAt(0).toString());
					}
					rights = newBuff.toString();
				}
				Integer right = Integer.valueOf(Integer.parseInt(rights, 2));
				this.docPrivilege.setRights(right);
				this.docPrivilegeService.save(this.docPrivilege);
				this.setJsonString("{success:true}");
			}
		} else {
			this.setJsonString("{success:false}");
		}
		return "success";
	}

	@Override
	public String save() {
		this.docPrivilegeService.save(this.docPrivilege);
		this.setJsonString("{success:true}");
		return "success";
	}

	public String add() {
		String strFolderId = this.getRequest().getParameter("folderId");
		String strRoleIds = this.getRequest().getParameter("roleIds");
		String strUserIds = this.getRequest().getParameter("userIds");
		String strDepIds = this.getRequest().getParameter("depIds");
		String strRightR = this.getRequest().getParameter("rightR");
		String strRightU = this.getRequest().getParameter("rightU");
		String strRightD = this.getRequest().getParameter("rightD");
		StringBuffer buff = new StringBuffer();

		if (StringUtils.isNotEmpty(strRightD)) {
			buff.append("1");
		} else {
			buff.append("0");
		}
		if (StringUtils.isNotEmpty(strRightU)) {
			buff.append("1");
		} else {
			buff.append("0");
		}
		if (StringUtils.isNotEmpty(strRightR)) {
			buff.append("1");
		} else {
			buff.append("0");
		}
		Integer rights = Integer.valueOf(Integer.parseInt(buff.toString(), 2));
		if (StringUtils.isNotEmpty(strFolderId)) {
			Long folderId = Long.valueOf(Long.parseLong(strFolderId));
			if (StringUtils.isNotEmpty(strRoleIds)) {
				String[] roles = strRoleIds.split(",");
				if (roles.length > 0) {
					for (String role : roles) {
						DocPrivilege docp = new DocPrivilege();
						docp.setFolderId(folderId);
						docp.setFlag((short) 3);
						Integer roleId = Integer
								.valueOf(Integer.parseInt(role));
						AppRole appRole = this.appRoleService.get(Long
								.valueOf(roleId.longValue()));
						docp.setUdrId(roleId);
						docp.setUdrName(appRole.getName());
						docp.setRights(rights);
						docp.setFdFlag((short) 0);
						this.docPrivilegeService.save(docp);
					}
				}
			}
			if (StringUtils.isNotEmpty(strUserIds)) {
				String[] userIds = strUserIds.split(",");
				if (userIds.length > 0) {
					for (String userId2 : userIds) {
						DocPrivilege docp = new DocPrivilege();
						docp.setFolderId(folderId);
						docp.setFlag(Short.valueOf((short) 1));
						Integer userId = Integer.valueOf(Integer
								.parseInt(userId2));
						AppUser appUser = this.appUserService.get(Long
								.valueOf(userId.longValue()));
						docp.setUdrId(userId);
						docp.setUdrName(appUser.getFullname());
						docp.setRights(rights);
						docp.setFdFlag(Short.valueOf((short) 0));
						this.docPrivilegeService.save(docp);
					}
				}
			}
			if (StringUtils.isNotEmpty(strDepIds)) {
				String[] depIds = strDepIds.split(",");
				if (depIds.length > 0) {
					for (String depId2 : depIds) {
						DocPrivilege docp = new DocPrivilege();
						docp.setFolderId(folderId);
						docp.setFlag(Short.valueOf((short) 2));
						Integer depId = Integer.valueOf(Integer
								.parseInt(depId2));
						Department department = this.departmentService.get(Long
								.valueOf(depId.longValue()));
						docp.setUdrId(depId);
						docp.setUdrName(department.getDepName());
						docp.setRights(rights);
						docp.setFdFlag(Short.valueOf((short) 0));
						this.docPrivilegeService.save(docp);
					}
				}
			}
		}
		this.setJsonString("{success:true}");
		return "success";
	}
}
