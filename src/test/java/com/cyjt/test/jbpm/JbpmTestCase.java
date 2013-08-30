package com.cyjt.test.jbpm;

import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.junit.Test;

import com.cyjt.core.util.XmlUtil;
import com.cyjt.oa.dao.flow.TaskDao;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProDefinitionService;
import com.cyjt.test.BaseTestCase;

public class JbpmTestCase extends BaseTestCase {

	@Resource
	private ProcessEngine processEngine;

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private ExecutionService executionService;

	@Resource
	private TaskService taskService;

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private TaskDao taskDao;

	public void deploy() {
		String id = this.repositoryService.createDeployment()
				.addResourceFromClasspath("com/cyjt/test/jbpm/test.jpdl.xml").deploy();
		System.out.println("deployId:" + id);
	}

	public void deploySignAll() {
		String id = this.repositoryService.createDeployment()
				.addResourceFromClasspath("com/cyjt/test/jbpm/signAll2.jpdl.xml").deploy();
		System.out.println("deployId:" + id);
	}

	public void getByTaskId() {
		String taskId = "68";
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			ProcessDefinitionImpl pd = task.getProcessInstance().getProcessDefinition();
			ActivityImpl activityFind = pd.findActivity("任务1");

			if (activityFind != null) {
				List list = activityFind.getOutgoingTransitions();
				System.out.println("size:" + list.size());
			}
		} finally {
			env.close();
		}
	}

	public void getExecutionId() {
		String exeId2 = "pd6717864949166496642.43.连线2";

		TaskImpl task = (TaskImpl) this.taskService.getTask("58");
		String deployId = "";
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		EnvironmentImpl env = environmentFactory.openEnvironment();
		try {
			deployId = task.getProcessInstance().getProcessDefinition().getDeploymentId();

			System.out.println("dpId:" + deployId);
		} finally {
			env.close();
		}

		ProDefinition pd = this.proDefinitionService.getByDeployId(deployId);

		System.out.println("taskName:" + task.getActivityName());

		System.out.println("pdxml:" + pd.getDefXml());
		Document doc = XmlUtil.stringToDocument(pd.getDefXml());

		Element rootEl = doc.getRootElement();

		Element taskEl = (Element) rootEl.selectSingleNode("/process/task[@name='" + task.getActivityName() + "']");

		if (taskEl != null) {
			List trans = taskEl.selectNodes("./transition");
			for (int i = 0; i < trans.size(); i++) {
				Element el = (Element) trans.get(i);
				System.out.println("transiton name:" + el.attributeValue("name"));
			}
		}
	}

	@Override
	public void test() {
	}

	public void completTask() {
		String taskId = "122";

		System.out.println("before instance:");

		this.taskService.completeTask(taskId);
		System.out.println("complet task");
	}

	public void signAll() {
		String deployId = "69";

		String piId = "signAll2.74";

		List<Task> userTaskList = this.taskService.findPersonalTasks("3");

		for (Task tk : userTaskList) {
			System.out.println("userid 1:  task:" + tk.getName() + " taskId:" + tk.getId());
		}

		List<Task> userTaskList6 = this.taskService.findPersonalTasks("6");

		for (Task tk : userTaskList6) {
			System.out.println("userid 6:  task:" + tk.getName() + " taskId:" + tk.getId());

			TaskImpl tm = (TaskImpl) this.taskService.getTask(tk.getId());

			if (tm != null) {
				System.out.println("sub task:" + tm.getName());
			} else {
				System.out.println(" task is null");
			}
			List transitions = this.jbpmService.getTransitionsByTaskId(tm.getId());

			System.out.println("size:" + transitions.size());
		}
	}

	public void unDeploy() {
		System.out.println("delete---------------");
		this.processEngine.getRepositoryService().deleteDeployment("2");
		System.out.println("delete- success--------------");
	}

	public void topAssign() {
	}

	@Test
	public void testGetValue() {
		List list = this.taskDao.getByActivityNameVarKeyLongVal("发文分发", "archives.archivesId", Long.valueOf(12L));

		System.out.println("size:" + list.size());
	}
}
