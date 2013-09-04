package com.palmelf.eoffice.action.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.JsonUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.admin.OfficeGoods;
import com.palmelf.eoffice.service.admin.OfficeGoodsService;

import flexjson.JSONSerializer;

public class OfficeGoodsAction extends BaseAction {

	@Resource
	private OfficeGoodsService officeGoodsService;
	private OfficeGoods officeGoods;
	private Long goodsId;

	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public OfficeGoods getOfficeGoods() {
		return this.officeGoods;
	}

	public void setOfficeGoods(OfficeGoods officeGoods) {
		this.officeGoods = officeGoods;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<OfficeGoods> list = this.officeGoodsService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.officeGoodsService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		OfficeGoods officeGoods = this.officeGoodsService.get(this.goodsId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = new JSONSerializer();
		sb.append(serializer.exclude(new String[] { "class" }).serialize(officeGoods));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");
		if (this.officeGoods.getGoodsId() == null) {
			this.officeGoods.setGoodsNo(sdf.format(new Date()));
			this.officeGoods.setStockCounts(Integer.valueOf(0));
		}
		this.officeGoodsService.save(this.officeGoods);
		this.setJsonString("{success:true}");
		return "success";
	}
}
