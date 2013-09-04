package com.palmelf.eoffice.action.flow;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.Constants;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.jbpm.jpdl.Node;
import com.palmelf.core.util.AppUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.flow.ExtFormItem;
import com.palmelf.eoffice.model.flow.FormDef;
import com.palmelf.eoffice.model.flow.ProDefinition;
import com.palmelf.eoffice.service.flow.FormDefService;
import com.palmelf.eoffice.service.flow.JbpmService;
import com.palmelf.eoffice.service.flow.ProDefinitionService;

public class FormDefAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private FormDefService formDefService;
	private FormDef formDef;
	private Long formDefId;
	private String defId;

	public String getDefId() {
		return this.defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public Long getFormDefId() {
		return this.formDefId;
	}

	public void setFormDefId(Long formDefId) {
		this.formDefId = formDefId;
	}

	public FormDef getFormDef() {
		return this.formDef;
	}

	public void setFormDef(FormDef formDef) {
		this.formDef = formDef;
	}

	public String nodes() {
		List<Node> nodes = this.jbpmService.getTaskNodesByDefId(new Long(this.defId));

		StringBuffer sb = new StringBuffer("{data:['");

		for (Node node : nodes) {
			sb.append("'").append(node.getName()).append("',");
		}
		sb.append("]}");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String addAll() {
		List<Node> nodes = this.jbpmService.getFormNodes(new Long(this.defId));
		ProDefinition proDefinition = this.proDefinitionService.get(new Long(this.defId));

		for (Node node : nodes) {
			FormDef formDef = this.formDefService.getByDeployIdActivityName(proDefinition.getDeployId(), node.getName());
			if (formDef == null) {
				formDef = new FormDef();
				formDef.setActivityName(node.getName());
				formDef.setColumns(FormDef.DEFAULT_COLUMNS);
				formDef.setFormName(node.getName() + "-表单");
				formDef.setIsEnabled(Constants.ENABLED);
				formDef.setDeployId(proDefinition.getDeployId());
				this.formDefService.save(formDef);
			}
		}
		this.setJsonString("{success:true}");
		return "success";
	}

	public String select() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<FormDef> list = this.formDefService.getAll(filter);

		Type type = new TypeToken<List<FormDef>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	@Override
	public String list() {
		ProDefinition proDefinition = this.proDefinitionService.get(new Long(this.defId));

		List<Node> nodes = this.jbpmService.getFormNodes(new Long(this.defId));

		List<FormDef> formDefs = this.formDefService.getByDeployId(proDefinition.getDeployId());

		StringBuffer buff = new StringBuffer("{result:[");

		for (int i = 0; i < nodes.size(); i++) {
			String nodeName = (nodes.get(i)).getName();
			buff.append("{activityName:'").append(nodeName).append("',deployId:'" + proDefinition.getDeployId()).append("'");

			for (FormDef def : formDefs) {
				if (nodeName.equals(def.getActivityName())) {
					buff.append(",formDefId:'").append(def.getFormDefId()).append("',formName:'").append(def.getFormName()).append("'");
					break;
				}
			}
			buff.append("},");
		}

		if (nodes.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}

		buff.append("]}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.formDefService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		FormDef formDef = this.formDefService.get(this.formDefId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(formDef));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String activityName = this.getRequest().getParameter("activityName");
		FormDef formDef = this.formDefService.get(this.formDefId);

		String extDef = this.getRequest().getParameter("extDef");
		formDef.setExtDef(extDef);
		this.formDefService.save(formDef);

		String extFormDef = this.getRequest().getParameter("extFormDef");
		String formItemDef = this.getRequest().getParameter("formItemDef");

		this.logger.info("extFormDef:" + extFormDef);
		this.logger.info("formItemDef:" + formItemDef);

		ProDefinition proDefinition = this.proDefinitionService.getByDeployId(formDef.getDeployId());
		String formPath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/" + proDefinition.getName();

		File flowDirPath = new File(formPath);
		if (!flowDirPath.exists()) {
			flowDirPath.mkdirs();
		}
		Gson gson = new Gson();

		ExtFormItem[] formItems = gson.fromJson("[" + formItemDef + "]", new TypeToken<ExtFormItem[]>() {
		}.getType());
		StringBuffer xmlBuf = new StringBuffer();

		if (formItems != null) {
			xmlBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xmlBuf.append("<fields>\n");
			for (ExtFormItem item : formItems) {
				xmlBuf.append("\t<field name=\"" + item.getName() + "\" label=\"" + item.getFieldLabel() + "\" type=\"" + item.getType()
				        + "\" length=\"" + item.getMaxLength() + "\" isShowed=\"" + item.getIsShowed() + "\"/>\n");
			}
			xmlBuf.append("</fields>\n");
		}

		if (xmlBuf.length() > 0) {
			String fieldFilePath = formPath + "/" + activityName + "-fields.xml";
			File file = new File(fieldFilePath);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileUtils.writeStringToFile(file, xmlBuf.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (proDefinition != null) {
			String extFilePath = formPath + "/" + activityName + ".vm";
			File file = new File(extFilePath);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileUtils.writeStringToFile(file, extFormDef);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	public String saveVmXml() {
		String deployId = this.getRequest().getParameter("deployId");
		String activityName = this.getRequest().getParameter("activityName");

		String vmSources = this.getRequest().getParameter("vmSources");

		String xmlSources = this.getRequest().getParameter("xmlSources");

		ProDefinition proDefinition = this.proDefinitionService.getByDeployId(deployId);
		String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/" + proDefinition.getName() + "/" + activityName;

		String vmFilePath = filePath + ".vm";
		String xmlFilePath = filePath + "-fields.xml";

		try {
	        FileUtils.writeStringToFile(new File(vmFilePath), vmSources);
	        FileUtils.writeStringToFile(new File(xmlFilePath), xmlSources);
        } catch (IOException e) {
	        e.printStackTrace();
        }
		

		this.setJsonString("{success:true}");

		return "success";
	}

	public String getVmXml() {
		String deployId = this.getRequest().getParameter("deployId");
		String activityName = this.getRequest().getParameter("activityName");

		ProDefinition proDefinition = this.proDefinitionService.getByDeployId(deployId);
		String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/" + proDefinition.getName() + "/" + activityName;

		String vmFilePath = filePath + ".vm";
		String xmlFilePath = filePath + "-fields.xml";

		String vmSources = null;
        try {
	        vmSources = FileUtils.readFileToString(new File(vmFilePath));
        } catch (IOException e) {
	        e.printStackTrace();
        }
		String xmlSources = null;
        try {
	        xmlSources = FileUtils.readFileToString(new File(xmlFilePath));
        } catch (IOException e) {
	        e.printStackTrace();
        }
		Gson gson = new Gson();

		this.setJsonString("{success:true,vmSources:" + gson.toJson(vmSources) + ",xmlSources:" + gson.toJson(xmlSources) + "}");

		return "success";
	}
}
