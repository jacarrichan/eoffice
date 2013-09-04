package com.palmelf.eoffice.action.hrm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.hrm.ContractAttach;
import com.palmelf.eoffice.service.hrm.ContractAttachService;

import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class ContractAttachAction extends BaseAction {

	@Resource
	private ContractAttachService contractAttachService;
	private ContractAttach contractAttach;
	private Long fileId;

	public Long getFileId() {
		return this.fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public ContractAttach getContractAttach() {
		return this.contractAttach;
	}

	public void setContractAttach(ContractAttach contractAttach) {
		this.contractAttach = contractAttach;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ContractAttach> list = this.contractAttachService.getAll(filter);

		Type type = new TypeToken<List<ContractAttach>>() {
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
				this.contractAttachService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ContractAttach contractAttach = this.contractAttachService
				.get(this.fileId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(contractAttach));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.contractAttachService.save(this.contractAttach);
		setJsonString("{success:true}");
		return "success";
	}
}
