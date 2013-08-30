package com.cyjt.oa.action.hrm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.hrm.EmpProfile;
import com.cyjt.oa.service.hrm.EmpProfileService;

import flexjson.JSONSerializer;

public class EmpProfileAction extends BaseAction {

	@Resource
	private EmpProfileService empProfileService;
	private EmpProfile empProfile;
	private Long profileId;

	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public EmpProfile getEmpProfile() {
		return this.empProfile;
	}

	public void setEmpProfile(EmpProfile empProfile) {
		this.empProfile = empProfile;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());

		List<EmpProfile> list = this.empProfileService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "birthday", "startWorkDate", "checktime",
				"createtime" });
		buff.append(serializer.exclude(new String[] { "class", "job.class", "job.department" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				EmpProfile deletePro = this.empProfileService.get(new Long(id));
				deletePro.setDelFlag(Short.valueOf(EmpProfile.DELETE_FLAG_HAD));
				this.empProfileService.save(deletePro);
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		EmpProfile empProfile = this.empProfileService.get(this.profileId);

		JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "birthday", "startWorkDate", "createtime",
				"checktime" });

		StringBuffer sb = new StringBuffer("{success:true,data:[");

		sb.append(json.exclude(new String[] { "class", "department" }).serialize(empProfile));
		sb.append("]}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		if (this.empProfile.getProfileId() == null) {
			if (this.empProfileService.checkProfileNo(this.empProfile.getProfileNo())) {
				this.empProfile.setCreator(ContextUtil.getCurrentUser().getFullname());
				this.empProfile.setCreatetime(new Date());
				this.empProfile.setDelFlag(Short.valueOf(EmpProfile.DELETE_FLAG_NOT));
				pass = true;
			} else {
				buff.append("msg:'档案编号已存在,请重新输入.',");
			}
		} else {
			pass = true;
		}

		if (pass) {
			this.empProfile.setApprovalStatus(Short.valueOf(EmpProfile.CHECK_FLAG_NONE));
			this.empProfileService.save(this.empProfile);
			buff.append("success:true}");
		} else {
			buff.append("failure:true}");
		}
		this.setJsonString(buff.toString());
		return "success";
	}

	public String number() {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");
		String profileNo = date.format(new Date());
		this.setJsonString("{success:true,profileNo:'PN" + profileNo + "'}");
		return "success";
	}

	public String check() {
		EmpProfile checkProfile = this.empProfileService.get(this.profileId);
		checkProfile.setCheckName(ContextUtil.getCurrentUser().getFullname());
		checkProfile.setChecktime(new Date());
		checkProfile.setApprovalStatus(this.empProfile.getApprovalStatus());
		checkProfile.setOpprovalOpinion(this.empProfile.getOpprovalOpinion());
		this.empProfileService.save(checkProfile);
		return "success";
	}

	public String recovery() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				EmpProfile deletePro = this.empProfileService.get(new Long(id));
				deletePro.setDelFlag(Short.valueOf(EmpProfile.DELETE_FLAG_NOT));
				this.empProfileService.save(deletePro);
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String delphoto() {
		if (this.profileId != null) {
			this.empProfile = (this.empProfileService.get(this.profileId));
			this.empProfile.setPhoto("");
			this.empProfileService.save(this.empProfile);
			this.jsonString = "{success:true}";
		}
		return "success";
	}
}
