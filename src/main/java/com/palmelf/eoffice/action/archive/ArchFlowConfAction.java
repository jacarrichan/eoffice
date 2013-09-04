package com.palmelf.eoffice.action.archive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.archive.ArchFlowConf;
import com.palmelf.eoffice.service.archive.ArchFlowConfService;

import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class ArchFlowConfAction extends BaseAction {

	@Resource
	private ArchFlowConfService archFlowConfService;
	private ArchFlowConf archFlowConf;
	private Long configId;

	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public ArchFlowConf getArchFlowConf() {
		return this.archFlowConf;
	}

	public void setArchFlowConf(ArchFlowConf archFlowConf) {
		this.archFlowConf = archFlowConf;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ArchFlowConf> list = this.archFlowConfService.getAll(filter);

		Type type = new TypeToken<List<ArchFlowConf>>() {
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
				this.archFlowConfService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchFlowConf sendFlowConf = this.archFlowConfService
				.getByFlowType(ArchFlowConf.ARCH_SEND_TYPE);
		ArchFlowConf recFlowConf = this.archFlowConfService
				.getByFlowType(ArchFlowConf.ARCH_REC_TYPE);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if (sendFlowConf != null)
			sb.append("{'sendProcessId':'" + sendFlowConf.getProcessDefId()
					+ "','sendProcessName':'" + sendFlowConf.getProcessName()
					+ "'");
		else {
			sb.append("{'sendProcessId':'','sendProcessName':''");
		}
		if (recFlowConf != null)
			sb.append(",'recProcessId':'" + recFlowConf.getProcessDefId()
					+ "','recProcessName':'" + recFlowConf.getProcessName()
					+ "'}}");
		else {
			sb.append(",'recProcessId':'','recProcessName':''}}");
		}
		setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		String sendId = getRequest().getParameter("sendProcessId");
		String sendName = getRequest().getParameter("sendProcessName");
		String recId = getRequest().getParameter("recProcessId");
		String recName = getRequest().getParameter("recProcessName");
		if ((StringUtils.isNotEmpty(sendId))
				&& (StringUtils.isNotEmpty(sendName))) {
			this.archFlowConf = this.archFlowConfService
					.getByFlowType(ArchFlowConf.ARCH_SEND_TYPE);
			if (this.archFlowConf == null) {
				this.archFlowConf = new ArchFlowConf();
				this.archFlowConf.setArchType(ArchFlowConf.ARCH_SEND_TYPE);
			}
			this.archFlowConf.setProcessDefId(new Long(sendId));
			this.archFlowConf.setProcessName(sendName);
			this.archFlowConfService.save(this.archFlowConf);
		}
		if ((StringUtils.isNotEmpty(recId))
				&& (StringUtils.isNotEmpty(recName))) {
			this.archFlowConf = this.archFlowConfService
					.getByFlowType(ArchFlowConf.ARCH_REC_TYPE);
			if (this.archFlowConf == null) {
				this.archFlowConf = new ArchFlowConf();
				this.archFlowConf.setArchType(ArchFlowConf.ARCH_REC_TYPE);
			}
			this.archFlowConf.setProcessDefId(new Long(recId));
			this.archFlowConf.setProcessName(recName);
			this.archFlowConfService.save(this.archFlowConf);
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String getFlow() {
		String type = getRequest().getParameter("flowType");
		StringBuffer sb = new StringBuffer();
		if (type.equals(ArchFlowConf.ARCH_SEND_TYPE.toString())) {
			this.archFlowConf = this.archFlowConfService
					.getByFlowType(ArchFlowConf.ARCH_SEND_TYPE);
		} else {
			this.archFlowConf = this.archFlowConfService
					.getByFlowType(ArchFlowConf.ARCH_REC_TYPE);
		}
		if (this.archFlowConf != null)
			sb.append("{success:true,defId:")
					.append(this.archFlowConf.getProcessDefId())
					.append(",processName:'")
					.append(this.archFlowConf.getProcessName()).append("'}");
		else {
			sb.append("{success:false,'message':'你还没设定流程'}");
		}
		setJsonString(sb.toString());
		return "success";
	}
}
