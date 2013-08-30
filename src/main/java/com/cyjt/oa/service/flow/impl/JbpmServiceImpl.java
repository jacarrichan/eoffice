package com.cyjt.oa.service.flow.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Session;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.svc.TaskServiceImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.cyjt.core.jbpm.jpdl.Node;
import com.cyjt.core.util.AppUtil;
import com.cyjt.oa.action.flow.FlowRunInfo;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.model.flow.ProUserAssign;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProDefinitionService;
import com.cyjt.oa.service.flow.ProUserAssignService;
import com.cyjt.oa.service.flow.ProcessRunService;
import com.cyjt.oa.service.system.UserSubService;

public class JbpmServiceImpl implements JbpmService {
	private static final Log logger = LogFactory.getLog(JbpmServiceImpl.class);

	@Resource
	private ProcessEngine processEngine;

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private ExecutionService executionService;

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private TaskService taskService;

	@Resource
	private HistoryService historyService;

	@Resource
	private ProUserAssignService proUserAssignService;

	@Resource
	private UserSubService userSubService;

	@Resource
	private ProcessRunService processRunService;

	public Task getTaskById(String taskId) {
		Task task = this.taskService.getTask(taskId);

		return task;
	}

	public void assignTask(String taskId, String userId) {
		this.taskService.assignTask(taskId, userId);
	}

	public void doUnDeployProDefinition(Long defId) {
		this.processRunService.removeByDefId(defId);

		ProDefinition pd = this.proDefinitionService.get(defId);
		if (pd != null) {
			this.repositoryService.deleteDeploymentCascade(pd.getDeployId());

			this.proDefinitionService.remove(pd);
		}
	}

	public ProDefinition saveOrUpdateDeploy(ProDefinition proDefinition) {
		if (proDefinition.getDeployId() == null) {
			if (JbpmServiceImpl.logger.isDebugEnabled()) {
				JbpmServiceImpl.logger.debug("deploy now===========");
			}
			String deployId = this.repositoryService
					.createDeployment()
					.addResourceFromString("process.jpdl.xml",
							proDefinition.getDefXml()).deploy();

			proDefinition.setDeployId(deployId);

			this.proDefinitionService.save(proDefinition);
		} else {
			this.proDefinitionService.evict(proDefinition);

			ProDefinition proDef = this.proDefinitionService.get(proDefinition
					.getDefId());

			if (!proDef.getDefXml().equals(proDefinition.getDefXml())) {
				if (proDef.getDeployId() != null) {
					this.repositoryService.deleteDeployment(proDef
							.getDeployId());
				}
				String deployId = this.repositoryService
						.createDeployment()
						.addResourceFromString("process.jpdl.xml",
								proDefinition.getDefXml()).deploy();
				proDefinition.setDeployId(deployId);
			}

			this.proDefinitionService.merge(proDefinition);
		}

		return proDefinition;
	}

	public ProcessDefinition getProcessDefinitionByKey(String processKey) {
		List list = this.repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processKey)
				.orderDesc("versionProperty.longValue").list();
		if ((list != null) && (list.size() > 0)) {
			return (ProcessDefinition) list.get(0);
		}
		return null;
	}

	public ProDefinition getProDefinitionByKey(String processKey) {
		ProcessDefinition processDefinition = this
				.getProcessDefinitionByKey(processKey);
		if (processDefinition != null) {
			ProDefinition proDef = this.proDefinitionService
					.getByDeployId(processDefinition.getDeploymentId());
			return proDef;
		}
		return null;
	}

	public String getDefinitionXmlByDefId(Long defId) {
		ProDefinition proDefinition = this.proDefinitionService.get(defId);
		return proDefinition.getDefXml();
	}

	public String getDefinitionXmlByDpId(String deployId) {
		ProDefinition proDefintion = this.proDefinitionService
				.getByDeployId(deployId);
		return proDefintion.getDefXml();
	}

	public String getDefinitionXmlByExeId(String exeId) {
		String pdId = this.executionService.findExecutionById(exeId)
				.getProcessDefinitionId();
		String deployId = this.repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(pdId).uniqueResult().getDeploymentId();
		return this.getDefinitionXmlByDpId(deployId);
	}

	public String getDefinitionXmlByPiId(String piId) {
		ProcessInstance pi = this.executionService.createProcessInstanceQuery()
				.processInstanceId(piId).uniqueResult();
		ProcessDefinition pd = this.repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(pi.getProcessDefinitionId())
				.uniqueResult();
		return this.getDefinitionXmlByDpId(pd.getDeploymentId());
	}

	public ProcessDefinition getProcessDefinitionByTaskId(String taskId) {
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);
		ProcessInstance pi = null;
		if (task.getSuperTask() != null) {
			pi = task.getSuperTask().getProcessInstance();
		} else {
			pi = task.getProcessInstance();
		}
		ProcessDefinition pd = this.repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(pi.getProcessDefinitionId())
				.uniqueResult();
		return pd;
	}

	public ProcessInstance getProcessInstance(String piId) {
		ProcessInstance pi = this.executionService.createProcessInstanceQuery()
				.processInstanceId(piId).uniqueResult();
		return pi;
	}

	public List<Node> getTaskNodesByDefId(Long defId) {
		ProDefinition proDefinition = this.proDefinitionService.get(defId);
		return this
				.getTaskNodesFromXml(proDefinition.getDefXml(), false, false);
	}

	public List<Node> getJumpNodesByDeployId(String deployId) {
		ProDefinition proDefinition = this.proDefinitionService
				.getByDeployId(deployId);
		return this.getTaskNodesFromXml(proDefinition.getDefXml(), false, true);
	}

	public List<Node> getFormNodes(Long defId) {
		ProDefinition proDefinition = this.proDefinitionService.get(defId);
		return this.getTaskNodesFromXml(proDefinition.getDefXml(), true, false);
	}

	public String getStartNodeName(ProDefinition proDefinition) {
		String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/"
				+ proDefinition.getName() + "/开始.vm";

		File file = new File(filePath);

		if (file.exists()) {
			return "开始";
		}
		try {
			Element root = DocumentHelper.parseText(proDefinition.getDefXml())
					.getRootElement();
			for (Element elem : (List<Element>) root.elements()) {
				String tagName = elem.getName();
				if ("start".equals(tagName)) {
					Attribute nameAttr = elem.attribute("name");
					if (nameAttr == null) {
						break;
					}
					return nameAttr.getValue();
				}
			}
		} catch (Exception ex) {
			JbpmServiceImpl.logger.error(ex.getMessage());
		}
		return "开始";
	}

	private List<Node> getTaskNodesFromXml(String xml, boolean includeStart,
			boolean includeEnd) {
		List<Node> nodes = new ArrayList<Node>();
		try {
			Element root = DocumentHelper.parseText(xml).getRootElement();
			for (Element elem : (List<Element>) root.elements()) {
				String type = elem.getQName().getName();
				if ("task".equalsIgnoreCase(type)) {
					if (elem.attribute("name") != null) {
						Node node = new Node(elem.attribute("name").getValue(),
								"任务节点");
						nodes.add(node);
					}
				} else if ((includeStart) && ("start".equalsIgnoreCase(type))) {
					if (elem.attribute("name") != null) {
						Node node = new Node(elem.attribute("name").getValue(),
								"开始节点");
						nodes.add(node);
					}
				} else if ((includeEnd) && (type.startsWith("end"))) {
					Node node = new Node(elem.attribute("name").getValue(),
							"结束节点");
					nodes.add(node);
				}
			}
		} catch (Exception ex) {
			JbpmServiceImpl.logger.error(ex.getMessage());
		}
		return nodes;
	}

	private List<Node> getValidNodesFromXml(String xml) {
		List<Node> nodes = new ArrayList<Node>();
		try {
			Element root = DocumentHelper.parseText(xml).getRootElement();
			for (Element elem : (List<Element>) root.elements()) {
				String type = elem.getQName().getName();
				if ("task".equalsIgnoreCase(type)) {
					if (elem.attribute("name") != null) {
						Node node = new Node(elem.attribute("name").getValue(),
								"任务节点");
						nodes.add(node);
					}
				} else if ("fork".equalsIgnoreCase(type)) {
					if (elem.attribute("name") != null) {
						Node node = new Node(elem.attribute("name").getValue(),
								"同步节点");
						nodes.add(node);
					}
				} else if ("join".equalsIgnoreCase(type)) {
					if (elem.attribute("name") != null) {
						Node node = new Node(elem.attribute("name").getValue(),
								"汇集节点");
						nodes.add(node);
					}
				} else if (type.startsWith("end")) {
					Node node = new Node(elem.attribute("name").getValue(),
							"结束节点");
					nodes.add(node);
				}
			}
		} catch (Exception ex) {
			JbpmServiceImpl.logger.error(ex.getMessage());
		}
		return nodes;
	}

	public String startProcess(String deployId, FlowRunInfo runInfo) {
		ProcessDefinitionImpl pd = (ProcessDefinitionImpl) this.repositoryService
				.createProcessDefinitionQuery().deploymentId(deployId)
				.uniqueResult();

		Map variables = runInfo.getVariables();
		logger.debug("开始一个新的流程实例,实例id为" + pd.getId());
		ProcessInstance pi = this.executionService.startProcessInstanceById(
				pd.getId(), variables);

		List taskList = this.getTasksByPiId(pi.getId());

		if (taskList.size() == 0) {
			return pi.getId();
		}
		Task task = (Task) taskList.get(0);
		String assignId = (String) variables.get("flowAssignId");
		boolean isTransitionExist = false;
		boolean isDaynic = false;
		String destName = runInfo.getDestName();
		String sourceName = task.getName();

		if (StringUtils.isNotEmpty(destName)) {
			String signalName = null;

			List<Transition> trans = this.getTransitionsByPiId(pi.getId());
			for (Transition tran : trans) {
				if ((tran.getDestination() == null)
						|| (!destName.equals(tran.getDestination().getName()))) {
					continue;
				}
				signalName = tran.getName();
				isTransitionExist = true;
				break;
			}

			if (!isTransitionExist) {
				this.addOutTransition(pd, sourceName, destName);
				signalName = "to" + destName;
				isDaynic = true;
			}
			this.taskService.completeTask(task.getId(), signalName);

			if (isDaynic) {
				this.removeOutTransition(pd, sourceName, destName);
			}

		}

		String signUserIds = (String) variables.get("signUserIds");

		if (StringUtils.isNotEmpty(signUserIds)) {
			List newTasks = this.getTasksByPiId(pi.getId());
			Iterator localIterator = newTasks.iterator();
			if (localIterator.hasNext()) {
				Task nTask = (Task) localIterator.next();
				this.newTask(nTask.getId(), signUserIds);
			}
		} else {
			this.assignTask(pi, pd, assignId, null);
		}

		return pi.getId();
	}

	public String startProcess(String deployId, Map variables) {
		ProcessDefinition pd = this.repositoryService
				.createProcessDefinitionQuery().deploymentId(deployId)
				.uniqueResult();
		this.clearSession();
		logger.debug("开始一个新的流程实例,实例id为" + pd.getId());
		ProcessInstance pi = this.executionService.startProcessInstanceById(
				pd.getId(), variables);
		String assignId = (String) variables.get("flowAssignId");

		String signUserIds = (String) variables.get("signUserIds");

		if (StringUtils.isNotEmpty(signUserIds)) {
			List newTasks = this.getTasksByPiId(pi.getId());
			Iterator localIterator = newTasks.iterator();
			if (localIterator.hasNext()) {
				Task nTask = (Task) localIterator.next();
				this.newTask(nTask.getId(), signUserIds);
			}
		} else {
			this.assignTask(pi, pd, assignId, null);
		}

		return pi.getId();
	}

	public ProcessInstance getProcessInstanceByExeId(String executionId) {
		Execution execution = this.executionService
				.findExecutionById(executionId);
		return (ProcessInstance) execution.getProcessInstance();
	}

	public ProcessInstance getProcessInstanceByTaskId(String taskId) {
		TaskImpl taskImpl = (TaskImpl) this.taskService.getTask(taskId
				.toString());
		if (taskImpl.getSuperTask() != null) {
			taskImpl = taskImpl.getSuperTask();
		}
		return taskImpl.getProcessInstance();
	}

	public void assignTask(ProcessInstance pi, ProcessDefinition pd,
			String assignId, String taskName) {
		if (pd == null) {
			pd = this.repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(pi.getProcessDefinitionId())
					.uniqueResult();
		}

		List<Task> taskList = null;
		if (StringUtils.isNotEmpty(taskName)) {
			taskList = this.taskService.createTaskQuery()
					.processInstanceId(pi.getId()).activityName(taskName)
					.list();
		}
		if ((taskList == null) || (taskList.size() == 0)) {
			taskList = this.getTasksByPiId(pi.getId());
		}

		for (Task task : taskList) {
			if (StringUtils.isNotEmpty(assignId)) {
				this.taskService.assignTask(task.getId(), assignId);
			} else {
				ProUserAssign assign = this.proUserAssignService
						.getByDeployIdActivityName(pd.getDeploymentId(),
								task.getActivityName());

				if (assign != null) {
					if ("__start".equals(assign.getUserId())) {
						AppUser flowStartUser = (AppUser) this.executionService
								.getVariable(pi.getId(), "flowStartUser");
						if (flowStartUser != null) {
							this.taskService.assignTask(task.getId(),
									flowStartUser.getUserId().toString());
						}
					} else if ("__super".equals(assign.getUserId())) {
						AppUser flowStartUser = (AppUser) this.executionService
								.getVariable(pi.getId(), "flowStartUser");

						if (flowStartUser != null) {
							List<Long> superUserIds = this.userSubService
									.upUser(flowStartUser.getUserId());
							StringBuffer upIds = new StringBuffer();
							for (Long userId : superUserIds) {
								upIds.append(userId).append(",");
							}
							if (superUserIds.size() > 0) {
								upIds.deleteCharAt(upIds.length() - 1);
							} else {
								upIds.append(flowStartUser.getUserId());
							}
							this.taskService
									.addTaskParticipatingUser(task.getId(),
											upIds.toString(), "candidate");
						}
					} else if (StringUtils.isNotEmpty(assign.getUserId())) {
						this.taskService.assignTask(task.getId(),
								assign.getUserId());
					}

					if (StringUtils.isNotEmpty(assign.getRoleId())) {
						this.taskService.addTaskParticipatingGroup(
								task.getId(), assign.getRoleId(), "candidate");
					}
				} else {
					AppUser flowStartUser = (AppUser) this.executionService
							.getVariable(pi.getId(), "flowStartUser");
					if (flowStartUser != null) {
						this.taskService.assignTask(task.getId(), flowStartUser
								.getUserId().toString());
					}
				}
			}
		}
	}

	public List<Transition> getTransitionsForSignalProcess(String piId) {
		ProcessInstance pi = this.executionService
				.findProcessInstanceById(piId);
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			ExecutionImpl executionImpl = (ExecutionImpl) pi;
			ActivityImpl activity = executionImpl.getActivity();
			List outTrans = activity.getOutgoingTransitions();
			return outTrans;
		} finally {
			env.close();
		}
	}

	public List<Transition> getTransitionsByTaskId(String taskId) {
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);
		if (task.getSuperTask() != null) {
			task = task.getSuperTask();
		}
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			ProcessDefinitionImpl pd = task.getProcessInstance()
					.getProcessDefinition();
			ActivityImpl activityFind = pd.findActivity(task.getActivityName());

			if (activityFind != null) {
				return (List<Transition>) activityFind.getOutgoingTransitions();
			}
		} finally {
			env.close();
		}
		return new ArrayList();
	}

	public List<Transition> getNodeOuterTrans(ProcessDefinition definition,
			String nodeName) {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			ProcessDefinitionImpl pd = (ProcessDefinitionImpl) definition;
			ActivityImpl activityFind = pd.findActivity(nodeName);

			if (activityFind != null) {
				return (List<Transition>) activityFind.getOutgoingTransitions();
			}
		} finally {
			env.close();
		}
		return new ArrayList();
	}

	public List<Transition> getTransitionsByPiId(String piId) {
		ExecutionImpl pi = (ExecutionImpl) this.getProcessInstance(piId);

		List taskList = this.getTasksByPiId(pi.getId());

		if (taskList.size() == 0) {
			return new ArrayList();
		}
		Task task = (Task) taskList.get(0);
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			ProcessDefinitionImpl pd = pi.getProcessDefinition();
			ActivityImpl activityFind = pd.findActivity(task.getActivityName());

			if (activityFind != null) {
				return (List<Transition>) activityFind.getOutgoingTransitions();
			}
		} finally {
			env.close();
		}
		return new ArrayList();
	}

	public void addOutTransition(ProcessDefinitionImpl pd, String sourceName,
			String destName) {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = null;
		try {
			env = environmentFactory.openEnvironment();

			ActivityImpl sourceActivity = pd.findActivity(sourceName);

			ActivityImpl destActivity = pd.findActivity(destName);

			TransitionImpl transition = sourceActivity
					.createOutgoingTransition();
			transition.setName("to" + destName);
			transition.setDestination(destActivity);

			sourceActivity.addOutgoingTransition(transition);
		} catch (Exception ex) {
			JbpmServiceImpl.logger.error(ex.getMessage());
		} finally {
			if (env != null) {
				env.close();
			}
		}
	}

	public void removeOutTransition(ProcessDefinitionImpl pd,
			String sourceName, String destName) {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = null;
		try {
			env = environmentFactory.openEnvironment();

			ActivityImpl sourceActivity = pd.findActivity(sourceName);

			List<Transition> trans = (List<Transition>) sourceActivity
					.getOutgoingTransitions();
			for (Transition tran : trans) {
				if (destName.equals(tran.getDestination().getName())) {
					trans.remove(tran);
					break;
				}
			}
		} catch (Exception ex) {
			JbpmServiceImpl.logger.error(ex.getMessage());
		} finally {
			if (env != null) {
				env.close();
			}
		}
	}

	public List<Transition> getFreeTransitionsByTaskId(String taskId) {
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);

		List outTrans = new ArrayList();

		if (task.getSuperTask() != null) {
			task = task.getSuperTask();
		}
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = null;
		try {
			env = environmentFactory.openEnvironment();
			ProcessDefinitionImpl pd = task.getProcessInstance()
					.getProcessDefinition();
			ActivityImpl curActivity = pd.findActivity(task.getActivityName());
			ProDefinition proDefinition = this.proDefinitionService
					.getByDeployId(pd.getDeploymentId());

			List<Node> allTaskNodes = this.getValidNodesFromXml(proDefinition
					.getDefXml());

			for (Node taskNode : allTaskNodes) {
				if (taskNode.getName().equals(task.getActivityName())) {
					continue;
				}
				TransitionImpl transition = curActivity
						.createOutgoingTransition();

				transition.setName("to" + taskNode.getName());
				transition.setDestination(pd.findActivity(taskNode.getName()));

				curActivity.getOutgoingTransitions().remove(transition);

				outTrans.add(transition);
			}
		} catch (Exception ex) {
			JbpmServiceImpl.logger.error(ex.getMessage());
		} finally {
			if (env != null) {
				env.close();
			}
		}

		return outTrans;
	}

	public String getProcessDefintionXMLByPiId(String piId) {
		ProcessRun processRun = this.processRunService.getByPiId(piId);
		return processRun.getProDefinition().getDefXml();
	}

	public List<Task> getTasksByPiId(String piId) {
		List taskList = this.taskService.createTaskQuery()
				.processInstanceId(piId).list();
		return taskList;
	}

	public String getNodeType(String xml, String nodeName) {
		String type = "";
		try {
			Element root = DocumentHelper.parseText(xml).getRootElement();
			for (Element elem : (List<Element>) root.elements()) {
				if (elem.attribute("name") != null) {
					String value = elem.attributeValue("name");
					if (value.equals(nodeName)) {
						type = elem.getQName().getName();
						return type;
					}
				}
			}
		} catch (Exception ex) {
			JbpmServiceImpl.logger.info(ex.getMessage());
		}
		return type;
	}

	private void clearSession() {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			Session session = env.get(Session.class);
			session.clear();
		} finally {
			env.close();
		}
	}

	private void flush() {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			Session session = env.get(Session.class);
			session.flush();
		} finally {
			env.close();
		}
	}

	public ProcessInstance completeTask(String taskId, String transitionName,
			String destName, Map variables) {
		TaskImpl taskImpl = (TaskImpl) this.taskService.getTask(taskId);

		String sourceName = taskImpl.getName();

		TaskImpl superTask = taskImpl.getSuperTask();

		ProcessDefinitionImpl pd = (ProcessDefinitionImpl) this
				.getProcessDefinitionByTaskId(taskId);
		ProcessInstance pi = null;

		boolean isTransitionExist = false;
		boolean isDynamicTran = false;

		if (destName != null) {
			List<Transition> trans = this.getTransitionsByTaskId(taskId);
			for (Transition tran : trans) {
				if ((tran.getDestination() == null)
						|| (!destName.equals(tran.getDestination().getName()))) {
					continue;
				}
				isTransitionExist = true;
				transitionName = tran.getName();
				break;
			}

			if (!isTransitionExist) {
				this.addOutTransition(pd, sourceName, destName);
				isDynamicTran = true;
			}

		}

		if (superTask != null) {
			pi = superTask.getProcessInstance();
			if (JbpmServiceImpl.logger.isDebugEnabled()) {
				JbpmServiceImpl.logger
						.debug("Super task is not null, task name is:"
								+ superTask.getActivityName());
			}

			if (superTask.getSubTasks() != null) {
				if (superTask.getSubTasks().size() == 1) {
					this.taskService.setVariables(taskId, variables);
					this.clearSession();

					this.taskService.completeTask(taskId);

					this.taskService.completeTask(superTask.getId(),
							transitionName);
				} else {
					this.taskService.setVariables(taskId, variables);
					this.clearSession();
					this.taskService.completeTask(taskId);

					return pi;
				}
			}
		} else {
			pi = taskImpl.getProcessInstance();
			this.taskService.setVariables(taskId, variables);

			this.taskService.completeTask(taskId, transitionName);
		}

		if (isDynamicTran) {
			this.removeOutTransition(pd, sourceName, destName);
		}

		boolean isEndProcess = this.isProcessInstanceEnd(pi.getId());
		if (isEndProcess) {
			ProcessRun processRun = this.processRunService
					.getByPiId(pi.getId());
			if (processRun != null) {
				processRun.setPiId(null);
				processRun.setRunStatus(ProcessRun.RUN_STATUS_FINISHED);
				this.processRunService.save(processRun);
			}
			return null;
		}

		String signUserIds = (String) variables.get("signUserIds");

		if ((destName != null) && (StringUtils.isNotEmpty(signUserIds))) {
			List<Task> newTasks = this.getTasksByPiId(pi.getId());
			for (Task nTask : newTasks) {
				if (destName.equals(nTask.getName())) {
					this.newTask(nTask.getId(), signUserIds);
					break;
				}
			}
			return pi;
		}
		destName = null;

		String assignId = (String) variables.get("flowAssignId");

		this.assignTask(pi, null, assignId, destName);

		return pi;
	}

	private boolean isProcessInstanceEnd(String piId) {
		HistoryProcessInstance hpi = this.historyService
				.createHistoryProcessInstanceQuery().processInstanceId(piId)
				.uniqueResult();
		if (hpi != null) {
			String endActivityName = ((HistoryProcessInstanceImpl) hpi)
					.getEndActivityName();
			if (endActivityName != null) {
				return true;
			}
		}
		return false;
	}

	public void newTask(String parentTaskId, String assignIds) {
		TaskServiceImpl taskServiceImpl = (TaskServiceImpl) this.taskService;
		Task parentTask = taskServiceImpl.getTask(parentTaskId);

		if (assignIds != null) {
			String[] userIds = assignIds.split("[,]");
			for (int i = 0; i < userIds.length; i++) {
				TaskImpl task = (TaskImpl) taskServiceImpl
						.newTask(parentTaskId);
				task.setAssignee(userIds[i]);
				task.setName(parentTask.getName() + "-" + (i + 1));
				task.setActivityName(parentTask.getName());
				task.setDescription(parentTask.getDescription());

				taskServiceImpl.saveTask(task);
			}
		}
	}

	public void signalProcess(String executionId, String transitionName,
			Map<String, Object> variables) {
		this.executionService.setVariables(executionId, variables);
		this.executionService.signalExecutionById(executionId, transitionName);
	}

	public void endProcessInstance(String piId) {
		ExecutionService executionService = this.processEngine
				.getExecutionService();
		executionService.endProcessInstance(piId, "ended");
	}
}
