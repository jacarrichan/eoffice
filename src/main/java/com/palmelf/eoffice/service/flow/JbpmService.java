package com.palmelf.eoffice.service.flow;

import com.palmelf.core.jbpm.jpdl.Node;
import com.palmelf.eoffice.action.flow.FlowRunInfo;
import com.palmelf.eoffice.model.flow.ProDefinition;

import java.util.List;
import java.util.Map;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;


public abstract interface JbpmService {
	public abstract ProcessInstance getProcessInstanceByExeId(String paramString);

	public abstract List<Node> getTaskNodesByDefId(Long paramLong);

	public abstract Task getTaskById(String paramString);

	public abstract void assignTask(String paramString1, String paramString2);

	public abstract List<Node> getFormNodes(Long paramLong);

	public abstract ProDefinition getProDefinitionByKey(String paramString);

	public abstract ProcessDefinition getProcessDefinitionByTaskId(
			String paramString);

	public abstract ProcessDefinition getProcessDefinitionByKey(
			String paramString);

	public abstract String getDefinitionXmlByDefId(Long paramLong);

	public abstract String getDefinitionXmlByDpId(String paramString);

	public abstract String getDefinitionXmlByPiId(String paramString);

	public abstract String getDefinitionXmlByExeId(String paramString);

	public abstract ProcessInstance getProcessInstance(String paramString);

	public abstract ProcessInstance getProcessInstanceByTaskId(
			String paramString);

	public abstract void doUnDeployProDefinition(Long paramLong);

	public abstract ProDefinition saveOrUpdateDeploy(
			ProDefinition paramProDefinition);

	public abstract String startProcess(String paramString, Map paramMap);

	public abstract String startProcess(String paramString,
			FlowRunInfo paramFlowRunInfo);

	public abstract List<Transition> getTransitionsForSignalProcess(
			String paramString);

	public abstract List<Transition> getTransitionsByTaskId(String paramString);

	public abstract List<Transition> getFreeTransitionsByTaskId(
			String paramString);

	public abstract String getNodeType(String paramString1, String paramString2);

	public abstract String getStartNodeName(ProDefinition paramProDefinition);

	public abstract String getProcessDefintionXMLByPiId(String paramString);

	public abstract List<Task> getTasksByPiId(String paramString);

	public abstract ProcessInstance completeTask(String paramString1,
			String paramString2, String paramString3, Map paramMap);

	public abstract void signalProcess(String paramString1,
			String paramString2, Map<String, Object> paramMap);

	public abstract void newTask(String paramString1, String paramString2);

	public abstract void endProcessInstance(String paramString);

	public abstract List<Transition> getNodeOuterTrans(
			ProcessDefinition paramProcessDefinition, String paramString);
}
