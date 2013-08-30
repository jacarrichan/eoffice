package com.cyjt.oa.action.personal;

import java.util.List;

import javax.annotation.Resource;

import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.personal.DutySection;
import com.cyjt.oa.service.personal.DutySectionService;

import flexjson.JSONSerializer;

public class DutySectionAction extends BaseAction {

	@Resource
	private DutySectionService dutySectionService;
	private DutySection dutySection;
	private Long sectionId;

	public Long getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public DutySection getDutySection() {
		return this.dutySection;
	}

	public void setDutySection(DutySection dutySection) {
		this.dutySection = dutySection;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List list = this.dutySectionService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);
		buff.append(serializer.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.dutySectionService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String combo() {
		StringBuffer sb = new StringBuffer();

		List<DutySection> dutySectionList = this.dutySectionService.getAll();
		sb.append("[");
		for (DutySection dutySection : dutySectionList) {
			sb.append("['").append(dutySection.getSectionId()).append("','")
					.append(dutySection.getSectionName()).append("'],");
		}
		if (dutySectionList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String get() {
		DutySection dutySection = this.dutySectionService.get(this.sectionId);

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.serialize(dutySection));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.dutySectionService.save(this.dutySection);
		this.setJsonString("{success:true}");
		return "success";
	}
}
