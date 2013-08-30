package com.cyjt.oa.service.flow.impl;

import com.cyjt.core.jbpm.pv.TaskInfo;
import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.flow.TaskDao;
import com.cyjt.oa.model.flow.JbpmTask;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.flow.ProcessRunService;
import com.cyjt.oa.service.flow.TaskService;
import com.cyjt.oa.service.system.AppUserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jbpm.pvm.internal.task.TaskImpl;

public class TaskServiceImpl extends BaseServiceImpl<TaskImpl> implements
		TaskService {

	@Resource
	private ProcessRunService processRunService;
	private TaskDao dao;

	@Resource
	private AppUserService appUserService;

	public TaskServiceImpl(TaskDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<TaskImpl> getTasksByUserId(String userId, PagingBean pb) {
		return this.dao.getTasksByUserId(userId, pb);
	}

	public List<TaskInfo> getTaskInfosByUserId(String userId, PagingBean pb,String reportType) {
		List<TaskImpl> list = getTasksByUserId(userId, pb);
		List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
		for (TaskImpl taskImpl : list) {
			TaskInfo taskInfo = new TaskInfo(taskImpl);
			
			if (taskImpl.getAssignee() != null) {
				AppUser user = this.appUserService.get(new Long(taskImpl
						.getAssignee()));
				taskInfo.setAssignee(user.getFullname());
			}
			ProcessRun processRun = this.processRunService.getByPiId(taskInfo
					.getPdId());
			if (processRun != null) {
				taskInfo.setTaskName(processRun.getProDefinition().getName()
						+ "--" + taskImpl.getActivityName());
				taskInfo.setActivityName(taskImpl.getActivityName());
			}
			//FIXME:XuXuebin Add 判断类型
			if(StringUtils.isNotEmpty(reportType) && processRun.getProDefinition().getDefId() == new Long(reportType)){
				taskInfoList.add(taskInfo);
			}else{
				taskInfoList.add(taskInfo);
			}
			
		}
		return taskInfoList;
	}

	public Set<Long> getHastenByActivityNameVarKeyLongVal(String activityName,
			String varKey, Long value) {
		List<JbpmTask> jtasks = this.dao.getByActivityNameVarKeyLongVal(
				activityName, varKey, value);
		Set<Long> userIds = new HashSet<Long>();
		for (JbpmTask jtask : jtasks) {
			if (jtask.getAssignee() == null) {
				List<Long> userlist = this.dao.getUserIdByTask(jtask.getTaskId());
				userIds.addAll(userlist);
				List<Long> groupList = this.dao.getGroupByTask(jtask
						.getTaskId());
				for (Long l : groupList) {
					List<AppUser> uList = this.appUserService.findByRoleId(l);
					List<Long> idList = new ArrayList<Long>();
					for (AppUser appUser : uList) {
						idList.add(appUser.getUserId());
					}
					userIds.addAll(idList);
				}
			} else {
				userIds.add(new Long(jtask.getAssignee()));
			}
		}
		return userIds;
	}

	public List<TaskImpl> getCandidateTasks(String userId, PagingBean pb) {
		return this.dao.getCandidateTasks(userId, pb);
	}

	public List<TaskImpl> getPersonTasks(String userId, PagingBean pb) {
		return this.dao.getPersonTasks(userId, pb);
	}
}
