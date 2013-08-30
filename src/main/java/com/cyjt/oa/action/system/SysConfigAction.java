package com.cyjt.oa.action.system;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.AppUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.system.SysConfig;
import com.cyjt.oa.service.system.SysConfigService;
import flexjson.JSONSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

public class SysConfigAction extends BaseAction {

	@Resource
	private SysConfigService sysConfigService;
	private SysConfig sysConfig;
	private Long configId;

	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public SysConfig getSysConfig() {
		return this.sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<SysConfig> list = this.sysConfigService.getAll(filter);

		Type type = new TypeToken<List<SysConfig>>() {
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
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.sysConfigService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		SysConfig sysConfig = this.sysConfigService.get(this.configId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(sysConfig));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		Map<String, Object> con = AppUtil.getSysConfig();
		Map map = getRequest().getParameterMap();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			SysConfig conf = this.sysConfigService.findByKey(key);
			String[] value = (String[]) entry.getValue();
			conf.setDataValue(value[0]);
			this.sysConfigService.save(conf);
			con.remove(key);
			con.put(key, value[0]);
		}

		AppUtil.reloadSysConfig();

		setJsonString("{success:true}");
		return "success";
	}

	public String load() {
		Map conf = this.sysConfigService.findByType();
		JSONSerializer json = new JSONSerializer();
		setJsonString("{success:true,data:" + json.deepSerialize(conf) + "}");
		return "success";
	}
}
