package com.cyjt.oa.service.flow.impl;

import com.cyjt.core.jbpm.pv.ParamField;
import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.action.flow.FlowRunInfo;
import com.cyjt.oa.dao.flow.FormDataDao;
import com.cyjt.oa.dao.flow.ProcessFormDao;
import com.cyjt.oa.dao.flow.ProcessRunDao;
import com.cyjt.oa.model.flow.FormData;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProcessRunService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ProcessInstance;

public class ProcessRunServiceImpl extends BaseServiceImpl<ProcessRun>
		implements ProcessRunService {
	private ProcessRunDao dao;

	@Resource
	private ProcessFormDao processFormDao;

	@Resource
	private FormDataDao formDataDao;

	@Resource
	private JbpmService jbpmService;

	public ProcessRunServiceImpl(ProcessRunDao dao) {
		super(dao);
		this.dao = dao;
	}

	public ProcessRun getByExeId(String exeId) {
		ProcessInstance pi = this.jbpmService.getProcessInstanceByExeId(exeId);
		if (pi != null) {
			return getByPiId(pi.getId());
		}
		return null;
	}

	public ProcessRun getByTaskId(String taskId) {
		ProcessInstance pi = this.jbpmService
				.getProcessInstanceByTaskId(taskId);
		if (pi != null) {
			return getByPiId(pi.getId());
		}
		return null;
	}

	public ProcessRun getByPiId(String piId) {
		return this.dao.getByPiId(piId);
	}

	public ProcessRun initNewProcessRun(ProDefinition proDefinition) {
		ProcessRun processRun = new ProcessRun();
		AppUser curUser = ContextUtil.getCurrentUser();

		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");

		processRun.setSubject(proDefinition.getName() + sdf.format(curDate)
				+ "(" + curUser.getFullname() + ")");
		processRun.setCreator(curUser.getFullname());
		processRun.setAppUser(curUser);
		processRun.setCreatetime(curDate);
		processRun.setProDefinition(proDefinition);

		return processRun;
	}

	public void saveProcessRun(ProcessRun processRun, ProcessForm processForm,
			FlowRunInfo runInfo) {
		logger.debug("AAAAA   saveProcessRun----------------");
		save(processRun);

		boolean isNewsForm = processForm.getFormId() == null;

		if (isNewsForm) {
			AppUser curUser = ContextUtil.getCurrentUser();

			processForm.setCreatorId(curUser.getUserId());
			processForm.setCreatorName(curUser.getFullname());
		}

		this.processFormDao.save(processForm);

		Iterator fieldNames = runInfo.getParamFields().keySet().iterator();

		while (fieldNames.hasNext()) {
			String fieldName = (String) fieldNames.next();
			ParamField paramField = runInfo.getParamFields().get(fieldName);
			FormData fd = null;
			if (!isNewsForm) {
				fd = this.formDataDao.getByFormIdFieldName(
						processForm.getFormId(), fieldName);
				fd.copyValue(paramField);
			} else {
				fd = new FormData(paramField);
			}
			fd.setProcessForm(processForm);

			if (runInfo.isStartFlow()) {
				runInfo.getVariables().put(fieldName, fd.getValue());
			}

			this.formDataDao.save(fd);
		}

		if (runInfo.isStartFlow()) {
			runInfo.getVariables().put("flowStartUser",
					ContextUtil.getCurrentUser());

			runInfo.getVariables().put("processName",
					processRun.getProDefinition().getName());

			String piId = this.jbpmService.startProcess(processRun
					.getProDefinition().getDeployId(), runInfo);
			processRun.setRunStatus(ProcessRun.RUN_STATUS_RUNNING);
			processRun.setPiId(piId);
			save(processRun);
		}
	}

	public ProcessInstance saveAndNextStep(FlowRunInfo runInfo) {
		ProcessInstance pi;
		if (StringUtils.isNotEmpty(runInfo.getTaskId()))
			pi = this.jbpmService.getProcessInstanceByTaskId(runInfo
					.getTaskId());
		else {
			pi = this.jbpmService.getProcessInstance(runInfo.getPiId());
		}

		String xml = this.jbpmService.getDefinitionXmlByPiId(pi.getId());

		String nodeType = this.jbpmService.getNodeType(xml,
				runInfo.getActivityName());

		ProcessRun processRun = getByPiId(pi.getId());

		Integer maxSn = Integer.valueOf(this.processFormDao.getActvityExeTimes(
				processRun.getRunId(), runInfo.getActivityName()).intValue());
		ProcessForm processForm = new ProcessForm();
		processForm.setActivityName(runInfo.getActivityName());
		processForm.setSn(Integer.valueOf(maxSn.intValue() + 1));

		AppUser curUser = ContextUtil.getCurrentUser();

		processForm.setCreatorId(curUser.getUserId());
		processForm.setCreatorName(curUser.getFullname());

		processForm.setProcessRun(processRun);

		Map variables = runInfo.getVariables();

		Iterator it = runInfo.getParamFields().keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			ParamField paramField = runInfo.getParamFields().get(key);
			FormData fd = new FormData(paramField);
			fd.setProcessForm(processForm);

			variables.put(key, fd.getValue());
			processForm.getFormDatas().add(fd);
		}

		this.processFormDao.save(processForm);

		if ("task".equals(nodeType)) {
			return this.jbpmService.completeTask(runInfo.getTaskId(),
					runInfo.getTransitionName(), runInfo.getDestName(),
					runInfo.getVariables());
		}
		this.jbpmService.signalProcess(pi.getId(), runInfo.getTransitionName(),
				variables);
		return pi;
	}

	@Override
	public void remove(Long runId) {
		ProcessRun processRun = this.dao.get(runId);
		if (ProcessRun.RUN_STATUS_INIT.equals(processRun.getRunStatus())) {
			List<ProcessForm> processForms = this.processFormDao
					.getByRunId(runId);
			for (ProcessForm processForm : processForms) {
				this.processFormDao.remove(processForm);
			}
		}
		this.dao.remove(processRun);
	}

	public void removeByDefId(Long defId) {
		List processRunList = this.dao.getByDefId(defId, new PagingBean(0, 25));
		for (int i = 0; i < processRunList.size(); i++) {
			this.dao.remove((ProcessRun) processRunList.get(i));
		}

		if (processRunList.size() == 25)
			removeByDefId(defId);
	}

	public List<ProcessRun> getByUserIdSubject(Long userId, String subject,
			PagingBean pb) {
		return this.dao.getByUserIdSubject(userId, subject, pb);
	}
}
