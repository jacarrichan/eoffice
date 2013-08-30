package com.cyjt.core.command;

import com.cyjt.core.util.ParamUtil;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ReportFilter {
	Map<String, Object> variables = new HashMap<String, Object>();

	public ReportFilter() {
	}

	public ReportFilter(HttpServletRequest request) {
		Enumeration<?> paramEnu = request.getParameterNames();
		while (paramEnu.hasMoreElements()) {
			String paramName = (String) paramEnu.nextElement();

			if (paramName.startsWith("Q_")) {
				String paramValue = request.getParameter(paramName);
				addFilter(paramName, paramValue);
			}
		}
	}

	public void addFilter(String paramName, String value) {
		String[] fieldInfo = paramName.split("[_]");
		if (fieldInfo.length == 3)
			this.variables.put(fieldInfo[1],
					ParamUtil.convertObject(fieldInfo[2], value));
	}

	public Map<String, Object> getVariables() {
		return this.variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}
