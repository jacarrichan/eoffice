package com.cyjt.oa.action.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.OfficeGoods;
import com.cyjt.oa.model.admin.OfficeGoodsType;
import com.cyjt.oa.service.admin.OfficeGoodsService;
import com.cyjt.oa.service.admin.OfficeGoodsTypeService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class OfficeGoodsTypeAction extends BaseAction {

	@Resource
	private OfficeGoodsTypeService officeGoodsTypeService;
	private OfficeGoodsType officeGoodsType;

	@Resource
	private OfficeGoodsService officeGoodsService;
	private Long typeId;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public OfficeGoodsType getOfficeGoodsType() {
		return this.officeGoodsType;
	}

	public void setOfficeGoodsType(OfficeGoodsType officeGoodsType) {
		this.officeGoodsType = officeGoodsType;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<OfficeGoodsType> list = this.officeGoodsTypeService.getAll(filter);
		Type type = new TypeToken<List<OfficeGoodsType>>() {
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

	public String tree() {
		String method = getRequest().getParameter("method");
		List<OfficeGoodsType> list = this.officeGoodsTypeService.getAll();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		if (StringUtils.isNotEmpty(method)) {
			sb.append("[");
		} else {
			i++;
			sb.append("[{id:'0',text:'办公用品分类',expanded:true,children:[");
		}
		for (OfficeGoodsType officeGoodsType : list) {
			sb.append("{id:'" + officeGoodsType.getTypeId() + "',text:'"
					+ officeGoodsType.getTypeName() + "',leaf:true},");
		}
		if (list.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		if (i == 0)
			sb.append("]");
		else {
			sb.append("]}]");
		}
		setJsonString(sb.toString());
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_officeGoodsType.typeId_L_EQ", id);
				List<OfficeGoods> list = this.officeGoodsService.getAll(filter);
				if (list.size() > 0) {
					this.jsonString = "{success:false,message:'该类型下还有用品，请转移后再删除！'}";
					return "success";
				}
				this.officeGoodsTypeService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		OfficeGoodsType officeGoodsType = this.officeGoodsTypeService
				.get(this.typeId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(officeGoodsType));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		this.officeGoodsTypeService.save(this.officeGoodsType);
		setJsonString("{success:true}");
		return "success";
	}
}
