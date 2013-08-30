package com.cyjt.oa.action.hrm;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.hrm.ContractEvent;
import com.cyjt.oa.model.hrm.UserContract;
import com.cyjt.oa.model.system.FileAttach;
import com.cyjt.oa.service.hrm.UserContractService;
import com.cyjt.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class UserContractAction extends BaseAction {

	@Resource
	private UserContractService userContractService;

	@Resource
	private FileAttachService fileAttachService;

	private UserContract userContract;
	private String data;
	private Long contractId;

	public Long getContractId() {
		return this.contractId;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public UserContract getUserContract() {
		return this.userContract;
	}

	public void setUserContract(UserContract userContract) {
		this.userContract = userContract;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<UserContract> list = this.userContractService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer json = new JSONSerializer();
		json.exclude(new String[] { "*.class" }).transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "signDate", "startDate", "expireDate" });

		buff.append(json.exclude(new String[] { "class", "contractEvents" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.userContractService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		UserContract userContract = this.userContractService.get(this.contractId);

		StringBuffer sb = new StringBuffer("{success:true,data:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
		sb.append(gson.toJson(userContract));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	public String Renew() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		String details = this.getRequest().getParameter("details");
		if (StringUtils.isNotEmpty(details)) {
			Gson gson = new Gson();
			ContractEvent[] contractEvents = (ContractEvent[]) gson.fromJson(details, new TypeToken<ContractEvent[]>() {
			}.getType());
			if ((contractEvents != null) && (contractEvents.length > 0)) {
				for (ContractEvent contractEvent : contractEvents) {
					contractEvent.setCreateTime(new Date());
					contractEvent.setCreator(ContextUtil.getCurrentUser().getFullname());
					contractEvent.setUserContract(this.userContract);
					this.userContract.getContractEvents().add(contractEvent);
				}
			}
		}
		if (this.userContract.getContractId() == null) {
			if (this.userContract.getStartDate().getTime() <= this.userContract.getExpireDate().getTime()) {
				pass = true;
			} else {
				buff.append("msg:'合同生效日期早于满约日期,请重新输入！',");
			}
		}

		if (pass) {
			this.userContract.setSignDate(new Date());
			this.userContractService.save(this.userContract);
			buff.append("success:true}");
		} else {
			try {
				UserContract contract = this.userContractService.get(this.userContract.getContractId());
				BeanUtil.copyNotNullProperties(contract, this.userContract);
				this.userContractService.save(contract);
			} catch (Exception e) {
				this.logger.error(e.getMessage());
			}
			buff.append("failure:true}");
		}
		this.setJsonString(buff.toString());
		return "success";
	}

	@Override
	public String save() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		String details = this.getRequest().getParameter("details");
		if (StringUtils.isNotEmpty(details)) {
			Gson gson = new Gson();
			ContractEvent[] contractEvents = (ContractEvent[]) gson.fromJson(details, new TypeToken<ContractEvent[]>() {
			}.getType());
			if ((contractEvents != null) && (contractEvents.length > 0)) {
				for (ContractEvent contractEvent : contractEvents) {
					contractEvent.setCreateTime(new Date());
					contractEvent.setCreator(ContextUtil.getCurrentUser().getFullname());
					contractEvent.setUserContract(this.userContract);
					this.userContract.getContractEvents().add(contractEvent);
				}
			}
		}

		if (this.userContract.getContractId() == null) {
			if ((this.userContract.getStartDate().getTime() <= this.userContract.getExpireDate().getTime())
					&& (this.userContractService.checkContractNo(this.userContract.getContractNo()))) {
				pass = true;
			} else {
				buff.append("msg:'合同编号已存在或生效日期早于满约日期,请重新输入',");
			}
		} else {
			pass = true;
		}

		if (pass) {
			String fileIds = this.getRequest().getParameter("fileIds");
			System.out.println(fileIds);

			if (StringUtils.isNotEmpty(fileIds)) {
				String[] fileId = fileIds.split(",");
				Set<FileAttach> contractAttachs = new HashSet<FileAttach>();
				for (String id : fileId) {
					if (id.equals("")) {
						continue;
					}
					contractAttachs.add(this.fileAttachService.get(new Long(id)));
				}

				this.userContract.setContractAttachs(contractAttachs);
			}
			this.userContractService.save(this.userContract);
			buff.append("success:true}");
		} else {
			try {
				UserContract contract = this.userContractService.get(this.userContract.getContractId());
				BeanUtil.copyNotNullProperties(contract, this.userContract);
				this.userContractService.save(contract);
			} catch (Exception e) {
				this.logger.error(e.getMessage());
			}
			buff.append("failure:true}");
		}
		this.setJsonString(buff.toString());
		return "success";
	}
}
