package com.cyjt.oa.action.archive;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.archive.ArchivesType;
import com.cyjt.oa.service.archive.ArchivesTypeService;

public class ArchivesTypeAction extends BaseAction {

	@Resource
	private ArchivesTypeService archivesTypeService;
	private ArchivesType archivesType;
	private Long typeId;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public ArchivesType getArchivesType() {
		return this.archivesType;
	}

	public void setArchivesType(ArchivesType archivesType) {
		this.archivesType = archivesType;
	}

	public String combo() {
		StringBuffer sb = new StringBuffer();

		List<ArchivesType> dutySectionList = this.archivesTypeService.getAll();
		sb.append("[");
		for (ArchivesType dutySection : dutySectionList) {
			sb.append("['").append(dutySection.getTypeId()).append("','")
					.append(dutySection.getTypeName()).append("'],");
		}
		if (dutySectionList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String tree() {
		List<ArchivesType> typeList = this.archivesTypeService.getAll();

		StringBuffer sb = new StringBuffer();
		sb.append("[{id:'0',text:'所有公文分类',expanded:true,children:[");
		for (ArchivesType type : typeList) {
			sb.append("{id:'" + type.getTypeId())
					.append("',text:'" + type.getTypeName())
					.append("',leaf:true,expanded:true},");
		}
		if (typeList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]}]");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<ArchivesType> list = this.archivesTypeService.getAll(filter);

		Type type = new TypeToken<List<ArchivesType>>() {
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

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archivesTypeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchivesType archivesType = this.archivesTypeService.get(this.typeId);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archivesType));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.archivesTypeService.save(this.archivesType);
		this.setJsonString("{success:true}");
		return "success";
	}
}
