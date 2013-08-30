package com.cyjt.oa.action.admin;

import java.util.List;

import javax.annotation.Resource;

import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.CartRepair;
import com.cyjt.oa.service.admin.CartRepairService;

import flexjson.JSONSerializer;

public class CartRepairAction extends BaseAction {

	@Resource
	private CartRepairService cartRepairService;
	private CartRepair cartRepair;
	private Long repairId;

	public Long getRepairId() {
		return this.repairId;
	}

	public void setRepairId(Long repairId) {
		this.repairId = repairId;
	}

	public CartRepair getCartRepair() {
		return this.cartRepair;
	}

	public void setCartRepair(CartRepair cartRepair) {
		this.cartRepair = cartRepair;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<CartRepair> list = this.cartRepairService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "repairDate" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.cartRepairService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		CartRepair cartRepair = this.cartRepairService.get(this.repairId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "repairDate" });
		sb.append(serializer.exclude(new String[] { "class", "car.cartRepairs" }).serialize(cartRepair));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		this.cartRepairService.save(this.cartRepair);
		this.setJsonString("{success:true}");
		return "success";
	}
}
