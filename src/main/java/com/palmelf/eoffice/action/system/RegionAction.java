package com.palmelf.eoffice.action.system;

import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.system.Region;
import com.palmelf.eoffice.service.system.RegionService;

public class RegionAction extends BaseAction {

	@Resource
	private RegionService regionService;
	private Region region;
	private Long regionId;

	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String list() {
		List<Region> list = null;
		StringBuffer buff = new StringBuffer("[");
		if (this.regionId == null) {
			list = this.regionService.getProvince();

			for (Region province : list) {
				buff.append("['" + province.getRegionId() + "','"
						+ province.getRegionName() + "'],");
			}
		} else {
			list = this.regionService.getCity(this.regionId);
			if (list.size() > 0) {
				for (Region city : list) {
					buff.append("'" + city.getRegionName() + "',");
				}
			} else {
				this.setRegion(this.regionService.get(this.regionId));
				buff.append("'" + this.region.getRegionName() + "',");
			}
		}
		buff.deleteCharAt(buff.length() - 1);
		buff.append("]");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String tree() {
		StringBuffer buff = new StringBuffer(
				"[{id:'0',text:'中国',expanded:true,children:[");
		List<Region> listProvince = this.regionService.getProvince();
		for (Region province : listProvince) {
			buff.append("{id:'" + province.getRegionId() + "',text:'"
					+ province.getRegionName() + "',");
			buff.append(this.findCity(province.getRegionId()));
		}
		if (!listProvince.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String findCity(Long regionId) {
		StringBuffer buff1 = new StringBuffer("");
		List<Region> listCity = this.regionService.getCity(regionId);
		if (listCity.size() == 0) {
			buff1.append("leaf:true},");
			return buff1.toString();
		}
		buff1.append("children:[");
		for (Region city : listCity) {
			buff1.append("{id:'" + city.getRegionId() + "',text:'"
					+ city.getRegionName() + "',leaf:true},");
		}

		buff1.deleteCharAt(buff1.length() - 1);
		buff1.append("]},");
		return buff1.toString();
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.regionService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Region region = this.regionService.get(this.regionId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(region));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.regionService.save(this.region);
		this.setJsonString("{success:true}");
		return "success";
	}
}
