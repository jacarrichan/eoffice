package com.palmelf.eoffice.action.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.JsonUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.admin.GoodsApply;
import com.palmelf.eoffice.service.admin.GoodsApplyService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class GoodsApplyAction extends BaseAction {

	@Resource
	private GoodsApplyService goodsApplyService;
	private GoodsApply goodsApply;
	private Long applyId;

	public Long getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public GoodsApply getGoodsApply() {
		return this.goodsApply;
	}

	public void setGoodsApply(GoodsApply goodsApply) {
		this.goodsApply = goodsApply;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<GoodsApply> list = this.goodsApplyService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.goodsApplyService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		GoodsApply goodsApply = this.goodsApplyService.get(this.applyId);
		StringBuffer sb = new StringBuffer("{success:true,data:");

		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude(new String[] { "class" });
		serializer.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "applyDate" });
		sb.append(serializer.serialize(goodsApply));

		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String check() {
		String approvalStatus = this.getRequest().getParameter("approvalStatus");
		String applyId = this.getRequest().getParameter("applyId");

		Integer status = this.goodsApplyService.check(new Long(applyId), new Short(approvalStatus));
		String msg = "";
		if (status.intValue() == 1) {
			msg = "成功审批~";
		} else if (status.intValue() == 2) {
			msg = "成功审批，不过目前库存不足，请联系相关人员采购~";
		} else {
			msg = "未通过审批";
		}
		this.setJsonString("{success:false,message:'" + msg + "'}");
		return "success";
	}

	@Override
	public String save() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");
		this.goodsApply.setApplyNo("GA" + sdf.format(new Date()));
		if (this.goodsApply.getApplyId() == null) {
			this.goodsApply.setApprovalStatus(GoodsApply.INIT_APPLY);
		}
		this.goodsApplyService.save(this.goodsApply);

		this.setJsonString("{success:true}");

		return "success";
	}
}
