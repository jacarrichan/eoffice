package com.cyjt.oa.action.flow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.jbpm.pv.TaskInfo;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.model.info.ShortMessage;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.flow.ProcessRunService;
import com.cyjt.oa.service.info.ShortMessageService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.jbpm.api.task.Task;

public class TaskAction extends BaseAction {

	@Resource(name = "flowTaskService")
	private com.cyjt.oa.service.flow.TaskService flowTaskService;

	@Resource
	private org.jbpm.api.TaskService taskService;

	@Resource
	private ShortMessageService shortMessageService;

	@Resource
	private ProcessRunService processRunService;
	
	@Override
	public String list() {
		PagingBean pb = new PagingBean(this.start.intValue(),
				this.limit.intValue());
		List<TaskInfo> tasks = this.flowTaskService.getTaskInfosByUserId(
				ContextUtil.getCurrentUserId().toString(), pb,null);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();

		buff.append(gson.toJson(tasks));

		buff.append("}");

		setJsonString(buff.toString());
		return "success";
	}

	public String change() {
		HttpServletRequest request = getRequest();
		String taskId = request.getParameter("taskId");
		String userId = request.getParameter("userId");
		String curUserId = ContextUtil.getCurrentUserId().toString();
		Task task = this.taskService.getTask(taskId);
		if ((task != null) && (curUserId.equals(task.getAssignee()))) {
			this.taskService.assignTask(taskId, userId);
			String msg = request.getParameter("msg");
			if (StringUtils.isNotEmpty(msg)) {
				this.shortMessageService.save(AppUser.SYSTEM_USER, userId, msg,
						ShortMessage.MSG_TYPE_TASK);
			}
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String unlock() {
		String taskId = getRequest().getParameter("taskId");
		Task task = this.taskService.getTask(taskId);

		String curUserId = ContextUtil.getCurrentUserId().toString();

		if ((task != null) && (curUserId.equals(task.getAssignee()))) {
			this.taskService.assignTask(task.getId(), null);
			setJsonString("{success:true,unlocked:true}");
		} else {
			setJsonString("{success:true,unlocked:false}");
		}

		return "success";
	}

	public String lock() {
		String taskId = getRequest().getParameter("taskId");
		Task task = this.taskService.getTask(taskId);

		if ((task != null) && (task.getAssignee() == null)) {
			this.taskService.assignTask(task.getId(), ContextUtil
					.getCurrentUserId().toString());
			setJsonString("{success:true,hasAssigned:false}");
		} else {
			setJsonString("{success:true,hasAssigned:true}");
		}

		return "success";
	}

	public String display() {
		PagingBean pb = new PagingBean(0, 8);
		List<TaskInfo> tasks = this.flowTaskService.getTaskInfosByUserId(
				ContextUtil.getCurrentUserId().toString(), pb,null);
		getRequest().setAttribute("taskList", tasks);
		return "display";
	}
	
	public String pendingReport(){
		PagingBean pb = new PagingBean(0, 8);
		String reportType = "2";
		List<TaskInfo> tasks = this.flowTaskService.getTaskInfosByUserId(
				ContextUtil.getCurrentUserId().toString(), pb,reportType);
		getRequest().setAttribute("taskList", tasks);
		return "display";
	}
	
	public String displayProcessRunFinish(){
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addFilter("Q_runStatus_SN_EQ", "2");
		List<ProcessRun> list = this.processRunService.getAll(filter);
		getRequest().setAttribute("processRunList", list);
		return "displayFinish";
	}

	public String check() {
		String taskId = getRequest().getParameter("taskId");
		Task task = this.taskService.getTask(taskId);
		String cruUserId = ContextUtil.getCurrentUserId().toString();
		if ((task != null) && (task.getAssignee() != null)
				&& (task.getAssignee().equals(cruUserId))) {
			setJsonString("{success:true}");
		} else if ((task != null) && (task.getAssignee() == null)) {
			this.taskService.assignTask(task.getId(), cruUserId);
			setJsonString("{success:true,assigned:true}");
		} else {
			setJsonString("{success:true,assigned:false}");
		}
		return "success";
	}
}
