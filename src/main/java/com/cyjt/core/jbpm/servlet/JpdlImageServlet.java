package com.cyjt.core.jbpm.servlet;

import com.cyjt.core.jbpm.jpdl.JpdlModel;
import com.cyjt.core.jbpm.jpdl.JpdlModelDrawer;
import com.cyjt.core.util.AppUtil;
import com.cyjt.oa.service.flow.JbpmService;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.ProcessInstance;

public class JpdlImageServlet extends HttpServlet {
	private Log logger = LogFactory.getLog(JpdlImageServlet.class);

	private JbpmService jbpmService = (JbpmService) AppUtil
			.getBean("jbpmService");

	public String getProcessDefintionXml(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");

		if (StringUtils.isNotEmpty(taskId)) {
			ProcessInstance pi = this.jbpmService
					.getProcessInstanceByTaskId(taskId);
			return this.jbpmService.getDefinitionXmlByPiId(pi.getId());
		}

		String deployId = request.getParameter("deployId");
		if (StringUtils.isNotEmpty(deployId)) {
			return this.jbpmService.getDefinitionXmlByDpId(deployId);
		}

		String piId = request.getParameter("piId");
		if ((StringUtils.isNotEmpty(piId)) && (!"null".equals(piId))) {
			return this.jbpmService.getDefinitionXmlByPiId(piId);
		}

		String defId = request.getParameter("defId");
		return this.jbpmService.getDefinitionXmlByDefId(new Long(defId));
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");

		String defXml = getProcessDefintionXml(request);
		try {
			JpdlModel jpdlModel = new JpdlModel(defXml);
			String taskId = request.getParameter("taskId");
			ProcessInstance pi = null;

			if (StringUtils.isNotEmpty(taskId)) {
				pi = this.jbpmService.getProcessInstanceByTaskId(taskId);
			} else {
				String piId = request.getParameter("piId");
				if (StringUtils.isNotEmpty(piId)) {
					pi = this.jbpmService.getProcessInstance(piId);
				}
			}
			if (pi != null) {
				Set activeActivityNames = pi.findActiveActivityNames();
				if (activeActivityNames != null) {
					jpdlModel.setActivityNames(activeActivityNames);
				}
			}
			response.setContentType("image/png");
			ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png",
					response.getOutputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
