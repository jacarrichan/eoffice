package com.palmelf.eoffice.action.flow;

import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.flow.ProType;
import com.palmelf.eoffice.service.flow.ProTypeService;

public class ProTypeAction extends BaseAction {

	@Resource
	private ProTypeService proTypeService;
	private ProType proType;
	private Long typeId;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public ProType getProType() {
		return this.proType;
	}

	public void setProType(ProType proType) {
		this.proType = proType;
	}

	@Override
	public String list() {
		List<ProType> processTypeList = this.proTypeService.getAll();
		StringBuffer sb = new StringBuffer("[");
		for (ProType proType : processTypeList) {
			sb.append("{id:'").append(proType.getTypeId()).append("',text:'")
					.append(proType.getTypeName()).append("',leaf:true},");
		}
		if (!processTypeList.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		this.jsonString = sb.toString();

		return "success";
	}

	public String root() {
		List<ProType> processTypeList = this.proTypeService.getAll();
		StringBuffer sb = new StringBuffer(
				"[{id:'0',text:'流程分类',leaf:false,expanded:true,children:[");
		for (ProType proType : processTypeList) {
			sb.append("{id:'").append(proType.getTypeId()).append("',text:'")
					.append(proType.getTypeName()).append("',leaf:true},");
		}
		if (!processTypeList.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]}]");
		this.jsonString = sb.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.proTypeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String remove() {
		this.proTypeService.remove(this.typeId);
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		ProType proType = this.proTypeService.get(this.typeId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proType));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.proTypeService.save(this.proType);
		this.setJsonString("{success:true}");
		return "success";
	}
}
