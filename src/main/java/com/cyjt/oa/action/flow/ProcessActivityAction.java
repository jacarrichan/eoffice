package com.cyjt.oa.action.flow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;

import org.jbpm.pvm.internal.task.ParticipationImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.Gson;
import com.cyjt.core.engine.MailEngine;
import com.cyjt.core.jbpm.pv.ParamField;
import com.cyjt.core.util.AppUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.util.StringUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.communicate.MobileMsg;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.model.flow.Transform;
import com.cyjt.oa.model.info.ShortMessage;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.communicate.MobileMsgService;
import com.cyjt.oa.service.flow.FormDataService;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProDefinitionService;
import com.cyjt.oa.service.flow.ProcessFormService;
import com.cyjt.oa.service.flow.ProcessRunService;
import com.cyjt.oa.service.info.ShortMessageService;
import com.cyjt.oa.service.system.AppUserService;

import flexjson.JSONSerializer;

public class ProcessActivityAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private ProcessFormService processFormService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private FormDataService formDataService;

	@Resource
	private ShortMessageService shortMessageService;

	@Resource
	private MobileMsgService mobileMsgService;

	@Resource
	private MailEngine mailEngine;

	@Resource
	VelocityEngine flowVelocityEngine;
	private String activityName;
	private Long runId;
	private Long taskId;
	private Long defId;

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Long getDefId() {
		return this.defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	public String get() throws Exception {
		ProDefinition proDefinition = this.getProDefinition();
		String processName = proDefinition.getName();

		if (StringUtils.isEmpty(this.activityName)) {
			this.activityName = this.jbpmService
					.getStartNodeName(proDefinition);
		}

		String tempLocation = ProcessActivityAssistant.getFormPath(processName,
				this.activityName);

		Map<String, Object> model = new HashMap<String, Object>();

		Map formDataMap = null;
		if (this.runId != null) {
			formDataMap = this.formDataService.getFromDataMap(this.runId,
					this.activityName);
		}

		Map fieldsMap = ProcessActivityAssistant.constructFieldMap(processName,
				this.activityName);

		Iterator fieldNames = fieldsMap.keySet().iterator();
		while (fieldNames.hasNext()) {
			String fieldName = (String) fieldNames.next();
			if (formDataMap != null) {
				Object fieldVal = formDataMap.get(fieldName);
				model.put(fieldName, fieldVal);
			}
			if (!model.containsKey(fieldName)) {
				model.put(fieldName, "");
			}

		}

		if (this.taskId != null) {
			ProcessRun processRun = this.processRunService
					.getByTaskId(this.taskId.toString());
			Map processRunVars = this.processFormService
					.getVariables(processRun.getRunId());

			List<Transition> trans = this.jbpmService
					.getTransitionsByTaskId(this.taskId.toString());

			List<Transform> allTrans = new ArrayList<Transform>();
			for (Transition tran : trans) {
				if (tran.getDestination() != null) {
					allTrans.add(new Transform(tran));
				}
			}

			model.putAll(processRunVars);

			model.put("nextTrans", allTrans);
		}
		model.put("activityName", this.activityName);
		model.put("currentUser", ContextUtil.getCurrentUser());
		model.put("dateTool", new DateTool());
		String formUiJs = "";
		try {
			formUiJs = VelocityEngineUtils.mergeTemplateIntoString(
					this.flowVelocityEngine, tempLocation, "UTF-8", model);
		} catch (Exception ex) {
			formUiJs = VelocityEngineUtils.mergeTemplateIntoString(
					this.flowVelocityEngine, ProcessActivityAssistant
							.getCommonFormPath(this.activityName), "UTF-8",
					model);
		}

		if (StringUtils.isEmpty(formUiJs)) {
			formUiJs = "[]";
		}
		this.setJsonString(formUiJs);

		return "success";
	}

	public String check() {
		Task task = this.jbpmService.getTaskById(String.valueOf(this.taskId));

		if (task != null) {
			String assignId = task.getAssignee();
			Long curUserId = ContextUtil.getCurrentUserId();

			if (curUserId.toString().equals(assignId)) {
				this.jsonString = "{success:true,isValid:true,msg:''}";
			} else if (StringUtils.isNotEmpty(assignId)) {
				this.jsonString = "{success:true,isValid:false,msg:'该任务已经被其他成员锁定执行！'}";
			} else {
				this.jbpmService.assignTask(task.getId(), curUserId.toString());
				this.jsonString = "{success:true,isValid:true,msg:'该任务已经被您锁定执行!'}";
			}
		} else {
			this.jsonString = "{success:true,isValid:false,msg:'该任务已经完成了'}";
		}

		return "success";
	}

	// TODO:发送短信
	@SuppressWarnings("unused")
	private void sendMsg() {
		String recUserId = this.getRequest().getParameter("recUserId");
		String msgContent = this.getRequest().getParameter("msgContent");

		if (this.logger.isDebugEnabled()) {
			this.logger.debug("recUserId:" + recUserId + " content:"
					+ msgContent);
		}

		if (StringUtils.isNotEmpty(recUserId)) {
			Long curUserId = ContextUtil.getCurrentUserId();
			this.shortMessageService.save(curUserId, recUserId, msgContent,
					ShortMessage.MSG_TYPE_TASK);
		}
	}

	@Override
	public String save() {
		FlowRunInfo flowRunInfo = this.getFlowRunInfo();
		if (this.runId != null) {
			ProcessRun processRun = this.processRunService.get(this.runId);
			ProcessForm processForm = this.processFormService
					.getByRunIdActivityName(this.runId, this.activityName);
			if (processForm != null) {
				this.processRunService.saveProcessRun(processRun, processForm,
						flowRunInfo);
			}
		} else if (this.defId != null) {
			ProcessRun processRun = this.initNewProcessRun();
			ProcessForm processForm = this.initNewProcessForm(processRun);
			this.processRunService.saveProcessRun(processRun, processForm,
					flowRunInfo);
			if (processRun.getPiId() != null) {
				ProcessInstance pi = this.jbpmService
						.getProcessInstance(processRun.getPiId());
				this.notice(pi);
			}
		}

		this.setJsonString("{success:true}");
		return "success";
	}

	private ProcessRun initNewProcessRun() {
		ProDefinition proDefinition = this.proDefinitionService.get(this.defId);

		return this.processRunService.initNewProcessRun(proDefinition);
	}

	private ProcessForm initNewProcessForm(ProcessRun processRun) {
		ProcessForm processForm = new ProcessForm();
		processForm.setActivityName(this.activityName);
		processForm.setProcessRun(processRun);

		return processForm;
	}

	public String next() {
		FlowRunInfo flowRunInfo = this.getFlowRunInfo();

		ProcessInstance pi = this.processRunService
				.saveAndNextStep(flowRunInfo);

		this.notice(pi);

		this.setJsonString("{success:true}");

		return "success";
	}

	private void notice(ProcessInstance pi) {
		if (pi != null) {
			List<Task> taskList = this.jbpmService.getTasksByPiId(pi.getId());

			for (Task task : taskList) {
				TaskImpl taskImpl = (TaskImpl) task;
				if (taskImpl.getAssignee() == null) {
					Iterator partIt = taskImpl.getAllParticipants().iterator();
					while (partIt.hasNext()) {
						ParticipationImpl part = (ParticipationImpl) partIt
								.next();
						if ((part.getGroupId() != null)
								&& (StringUtil.isNum(part.getGroupId()))) {
							List<AppUser> appUserList = this.appUserService
									.findByRoleId(new Long(part.getGroupId()));
							for (AppUser user : appUserList) {
								this.sendMailNotice(taskImpl, user);
							}
						} else if ((part.getUserId() != null)
								&& (StringUtil.isNum(part.getUserId()))) {
							AppUser appUser = this.appUserService.get(new Long(
									part.getUserId()));
							this.sendMailNotice(taskImpl, appUser);
						}
					}
				} else if (StringUtil.isNum(taskImpl.getAssignee())) {
					AppUser appUser = this.appUserService.get(new Long(taskImpl
							.getAssignee()));
					this.sendMailNotice(taskImpl, appUser);
				}
			}
		}
	}

	private void sendMailNotice(Task task, AppUser appUser) {
		String sendMail = this.getRequest().getParameter("sendMail");
		String sendMsg = this.getRequest().getParameter("sendMsg");

		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String curDateStr = sdf.format(curDate);
		if ("true".equals(sendMail)) {
			if (appUser.getEmail() != null) {
				if (this.logger.isDebugEnabled()) {
					this.logger.info("Notice " + appUser.getFullname()
							+ " by mail:" + appUser.getEmail());
				}

				String tempPath = "mail/flowMail.vm";
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("curDateStr", curDateStr);
				model.put("appUser", appUser);
				model.put("task", task);
				String subject = "来自" + AppUtil.getCompanyName() + "办公系统的待办任务("
						+ task.getName() + ")提醒";
				this.mailEngine.sendTemplateMail(tempPath, model, subject,
						null, new String[] { appUser.getEmail() }, null, null,
						null, null, true);
			}
		}

		if ("true".equals(sendMsg)) {
			if (appUser.getMobile() != null) {
				if (this.logger.isDebugEnabled()) {
					this.logger.info("Notice " + appUser.getFullname()
							+ " by mobile:" + appUser.getMobile());
				}

				String content = AppUtil.getCompanyName() + "办公系统于"
						+ curDateStr + "产生了一项待办事项(" + task.getName()
						+ ")，请您在规定时间内完成审批~";
				MobileMsg mobileMsg = new MobileMsg();
				mobileMsg.setContent(content);
				mobileMsg.setMobileNo(appUser.getMobile());
				mobileMsg.setCreatetime(curDate);
				mobileMsg.setStatus(MobileMsg.STATUS_INIT);
				this.mobileMsgService.save(mobileMsg);
			}
		}
	}

	public String outerTrans() {
		ProcessDefinition pd = this.jbpmService
				.getProcessDefinitionByTaskId(this.taskId.toString());
		String nodeName = this.getRequest().getParameter("nodeName");
		List<Transition> trans = this.jbpmService.getNodeOuterTrans(pd,
				nodeName);

		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();

		sb.append("[");

		for (Transition tran : trans) {
			sb.append("[").append(gson.toJson(tran.getName())).append(",")
					.append(gson.toJson(tran.getDestination().getName()))
					.append(",")
					.append(gson.toJson(tran.getDestination().getType()))
					.append("],");
		}

		if (trans.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append("]");

		this.setJsonString(sb.toString());

		return "success";
	}

	public String freeTrans() {
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();

		sb.append("[");

		List<Transition> trans = this.jbpmService
				.getFreeTransitionsByTaskId(this.taskId.toString());

		for (Transition tran : trans) {
			sb.append("[").append(gson.toJson(tran.getName())).append(",")
					.append(gson.toJson(tran.getDestination().getName()))
					.append(",")
					.append(gson.toJson(tran.getDestination().getType()))
					.append("],");
		}

		if (trans.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append("]");

		this.setJsonString(sb.toString());

		return "success";
	}

	public String trans() {
		List<Transform> allTrans = new ArrayList<Transform>();

		List<Transition> trans = this.jbpmService
				.getTransitionsByTaskId(this.taskId.toString());

		for (Transition tran : trans) {
			if (tran.getDestination() != null) {
				allTrans.add(new Transform(tran));
			}
		}

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);
		String result = serializer.serialize(allTrans);

		this.setJsonString("{success:true,data:" + result + "}");
		return "success";
	}

	private Map<String, ParamField> constructFieldMap() {
		HttpServletRequest request = this.getRequest();
		ProDefinition proDefinition = this.getProDefinition();

		if (StringUtils.isEmpty(this.activityName)) {
			this.activityName = this.jbpmService
					.getStartNodeName(proDefinition);
		}

		Map<String, ParamField> map = ProcessActivityAssistant
				.constructFieldMap(proDefinition.getName(), this.activityName);

		Iterator<String> fieldNames = map.keySet().iterator();
		while (fieldNames.hasNext()) {
			String name = fieldNames.next();
			ParamField pf = map.get(name);

			pf.setName(pf.getName().replace(".", "_"));
			pf.setValue(request.getParameter(name));
		}
		return map;
	}

	private ProDefinition getProDefinition() {
		ProDefinition proDefinition = null;
		if (this.runId != null) {
			ProcessRun processRun = this.processRunService.get(this.runId);
			proDefinition = processRun.getProDefinition();
		} else if (this.defId != null) {
			proDefinition = this.proDefinitionService.get(this.defId);
		} else {
			ProcessRun processRun = this.processRunService
					.getByTaskId(this.taskId.toString());
			proDefinition = processRun.getProDefinition();
		}
		return proDefinition;
	}

	private FlowRunInfo getFlowRunInfo() {
		FlowRunInfo info = new FlowRunInfo(this.getRequest());
		Map<String, ParamField> fieldMap = this.constructFieldMap();
		info.setParamFields(fieldMap);
		return info;
	}
}
