package com.palmelf.eoffice.action.personal;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.BeanUtil;
import com.palmelf.core.util.JsonUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.personal.Duty;
import com.palmelf.eoffice.model.personal.DutySystem;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.personal.DutyService;
import com.palmelf.eoffice.service.personal.DutySystemService;
import com.palmelf.eoffice.service.system.AppUserService;

import flexjson.JSONSerializer;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class DutyAction extends BaseAction {

	@Resource
	private DutyService dutyService;

	@Resource
	private DutySystemService dutySystemService;

	@Resource
	private AppUserService appUserService;
	private Duty duty;
	private Long dutyId;

	public Long getDutyId() {
		return this.dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public Duty getDuty() {
		return this.duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.dutyService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"startTime", "endTime" });
		buff.append(serializer.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.dutyService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Duty duty = this.dutyService.get(this.dutyId);
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"startTime", "endTime" });

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.serialize(duty));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String systemId = getRequest().getParameter("dutySystemId");

		if (StringUtils.isNotEmpty(systemId)) {
			DutySystem dutySystem = this.dutySystemService.get(new Long(
					systemId));
			this.duty.setDutySystem(dutySystem);
		}

		String userId = getRequest().getParameter("duty.userId");

		String[] uIds = userId.split("[,]");

		StringBuffer ssb = new StringBuffer("");
		boolean isExcept = false;
		if (uIds != null) {
			for (int i = 0; i < uIds.length; i++) {
				AppUser user = this.appUserService.get(new Long(uIds[i]));
				Duty uDuty = new Duty();
				try {
					BeanUtil.copyNotNullProperties(uDuty, this.duty);

					boolean isExist = false;
					if (uDuty.getDutyId() == null) {
						isExist = this.dutyService.isExistDutyForUser(
								user.getUserId(), uDuty.getStartTime(),
								uDuty.getEndTime());
					}
					if (isExist) {
						isExcept = true;
						ssb.append(user.getFullname()).append(",");
					} else {
						uDuty.setAppUser(user);
						uDuty.setFullname(user.getFullname());
						this.dutyService.save(uDuty);
					}
				} catch (Exception ex) {
					this.logger.error("error:" + ex.getMessage());
				}
			}

		}

		if (isExcept) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ssb.append("在该时间(").append(sdf.format(this.duty.getStartTime()))
					.append("至").append(sdf.format(this.duty.getEndTime()))
					.append(")内已经存在班制!");
		}

		setJsonString("{success:true,exception:'" + ssb.toString() + "'}");
		return "success";
	}
}
