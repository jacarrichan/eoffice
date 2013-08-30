package com.cyjt.oa.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.system.Dictionary;
import com.cyjt.oa.model.system.GlobalType;
import com.cyjt.oa.service.system.DictionaryService;
import com.cyjt.oa.service.system.GlobalTypeService;

import flexjson.JSONSerializer;

public class DictionaryAction extends BaseAction {

	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private GlobalTypeService globalTypeService;
	private Dictionary dictionary;
	private Long dicId;
	private String itemName;

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getDicId() {
		return this.dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}

	public Dictionary getDictionary() {
		return this.dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public String mulSave() {
		String data = this.getRequest().getParameter("data");

		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			Dictionary[] dics = (Dictionary[]) gson.fromJson(data,
					new TypeToken<Dictionary[]>() {
					}.getType());

			for (Dictionary dic2 : dics) {
				Dictionary dic = this.dictionaryService.get(dic2.getDicId());
				try {
					BeanUtil.copyNotNullProperties(dic, dic2);

					this.dictionaryService.save(dic);
				} catch (Exception ex) {
					this.logger.error(ex.getMessage());
				}
			}
		}

		this.jsonString = "{success:true}";
		return "success";
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		String sParentId = this.getRequest().getParameter("parentId");
		if ((StringUtils.isNotEmpty(sParentId)) && (!"0".equals(sParentId))) {
			GlobalType globalType = this.globalTypeService.get(new Long(
					sParentId));
			filter.addFilter("Q_globalType.path_S_LFK", globalType.getPath());
		}
		List list = this.dictionaryService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = new JSONSerializer();
		buff.append(json.serialize(list));

		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String load() {
		List<String> list = this.dictionaryService
				.getAllByItemName(this.itemName);
		StringBuffer buff = new StringBuffer("[");
		for (String itemName : list) {
			buff.append("'").append(itemName).append("',");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String loadItem() {
		List<Dictionary> list = this.dictionaryService
				.getByItemName(this.itemName);
		StringBuffer buff = new StringBuffer("[");
		for (Dictionary dic : list) {
			buff.append("[").append(dic.getDicId()).append(",'")
					.append(dic.getItemValue()).append("'],");
		}

		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String loadItemRecord() {
		List list = this.dictionaryService.getByItemName(this.itemName);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(list));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String typeChange() {
		String dicIds = this.getRequest().getParameter("dicIds");
		String dicTypeId = this.getRequest().getParameter("dicTypeId");

		if ((StringUtils.isNotEmpty(dicIds))
				&& (StringUtils.isNotEmpty(dicTypeId))) {
			GlobalType globalType = this.globalTypeService.get(new Long(
					dicTypeId));

			String[] ids = dicIds.split("[,]");
			if (ids != null) {
				for (String id : ids) {
					Dictionary dic = this.dictionaryService.get(new Long(id));
					dic.setGlobalType(globalType);
					dic.setItemName(globalType.getTypeName());

					this.dictionaryService.save(dic);
				}
			}
		}
		this.setJsonString("{success:true}");
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.dictionaryService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Dictionary dictionary = this.dictionaryService.get(this.dicId);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(dictionary));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.dictionary.getDicId() != null) {
			Dictionary orgDic = this.dictionaryService.get(this.dictionary
					.getDicId());
			try {
				BeanUtil.copyNotNullProperties(orgDic, this.dictionary);
				this.dictionaryService.save(orgDic);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		} else {
			String parentId = this.getRequest().getParameter("parentId");
			if (StringUtils.isNotEmpty(parentId)) {
				GlobalType globalType = this.globalTypeService.get(new Long(
						parentId));
				this.dictionary.setGlobalType(globalType);
			}
			this.dictionaryService.save(this.dictionary);
		}
		this.setJsonString("{success:true}");
		return "success";
	}

	public String items() {
		List<String> list = this.dictionaryService.getAllItems();
		StringBuffer buff = new StringBuffer("[");
		for (String str : list) {
			buff.append("'").append(str).append("',");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		this.setJsonString(buff.toString());
		return "success";
	}
}
