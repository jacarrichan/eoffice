package com.cyjt.oa.action.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.Car;
import com.cyjt.oa.model.admin.CarApply;
import com.cyjt.oa.model.info.ShortMessage;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.admin.CarApplyService;
import com.cyjt.oa.service.admin.CarService;
import com.cyjt.oa.service.info.ShortMessageService;

import flexjson.JSONSerializer;

public class CarApplyAction extends BaseAction {

	@Resource
	private CarApplyService carApplyService;
	private CarApply carApply;

	@Resource
	private ShortMessageService shortMessageService;

	@Resource
	private CarService carService;
	private Long applyId;

	public Long getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public CarApply getCarApply() {
		return this.carApply;
	}

	public void setCarApply(CarApply carApply) {
		this.carApply = carApply;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<CarApply> list = this.carApplyService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate", "startTime", "endTime" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.carApplyService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		CarApply carApply = this.carApplyService.get(this.applyId);
		StringBuffer sb = new StringBuffer("{success:true,data:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate", "startTime", "endTime" });
		sb.append(serializer.exclude(new String[] { "class", "car.carApplys" }).serialize(carApply));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String check() {
		String applyId = this.getRequest().getParameter("applyId");
		String approvalStatus = this.getRequest().getParameter("approvalStatus");

		this.carApply = (this.carApplyService.get(new Long(applyId)));
		this.carApply.setApprovalStatus(new Short(approvalStatus));
		this.carApplyService.save(this.carApply);

		Long receiveId = this.carApply.getUserId();
		Car car = this.carService.get(this.carApply.getCar().getCarId());

		String content = "你申请的车牌号为" + car.getCarNo() + "已经通过审批.";
		if (this.carApply.getApprovalStatus() == CarApply.NOTPASS_APPLY) {
			content = "你申请的车牌号为" + car.getCarNo() + "没有通过审批.";
		}
		this.shortMessageService.save(AppUser.SYSTEM_USER, receiveId.toString(), content, ShortMessage.MSG_TYPE_SYS);
		this.setJsonString("{success:true,message:'已经成功提交审批~'}");
		return "success";
	}

	@Override
	public String save() {
		if (this.carApply.getApprovalStatus() == null) {
			this.carApply.setApprovalStatus(Short.valueOf(Car.NOTPASS_APPLY));
			this.carApply.setApplyDate(new Date());
		}
		this.carApplyService.save(this.carApply);
		this.setJsonString("{success:true}");
		return "success";
	}
}
