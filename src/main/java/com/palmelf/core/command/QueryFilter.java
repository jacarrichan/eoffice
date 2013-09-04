package com.palmelf.core.command;

import com.palmelf.core.util.ParamUtil;
import com.palmelf.core.web.paging.PagingBean;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueryFilter {
	public static final String ORDER_DESC = "desc";
	public static final String ORDER_ASC = "asc";
	public static final Log logger = LogFactory.getLog(QueryFilter.class);

	private HttpServletRequest request = null;

	private String filterName = null;

	private List<Object> paramValues = new ArrayList<Object>();

	private List<CriteriaCommand> commands = new ArrayList<CriteriaCommand>();

	private Set<String> aliasSet = new HashSet<String>();

	private PagingBean pagingBean = null;

	public String getFilterName() {
		return this.filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public PagingBean getPagingBean() {
		return this.pagingBean;
	}

	public QueryFilter(HttpServletRequest request) {
		this.request = request;
		Enumeration<?> paramEnu = request.getParameterNames();
		while (paramEnu.hasMoreElements()) {
			String paramName = (String) paramEnu.nextElement();

			if (paramName.startsWith("Q_")) {
				String paramValue = request.getParameter(paramName);
				addFilter(paramName, paramValue);
			}
		}

		Integer start = Integer.valueOf(0);
		Integer limit = PagingBean.DEFAULT_PAGE_SIZE;

		String s_start = request.getParameter("start");
		String s_limit = request.getParameter("limit");
		if (StringUtils.isNotEmpty(s_start)) {
			start = new Integer(s_start);
		}
		if (StringUtils.isNotEmpty(s_limit)) {
			limit = new Integer(s_limit);
		}

		String sort = request.getParameter("sort");
		String dir = request.getParameter("dir");

		if ((StringUtils.isNotEmpty(sort)) && (StringUtils.isNotEmpty(dir))) {
			addSorted(sort, dir);
		}

		this.pagingBean = new PagingBean(start.intValue(), limit.intValue());
	}

	public void addFilter(String paramName, String paramValue) {
		String[] fieldInfo = paramName.split("[_]");
		Object value = null;
		if ((fieldInfo != null) && (fieldInfo.length == 4)) {
			value = ParamUtil.convertObject(fieldInfo[2], paramValue);
			if (value != null) {
				FieldCommandImpl fieldCommand = new FieldCommandImpl(
						fieldInfo[1], value, fieldInfo[3], this);
				this.commands.add(fieldCommand);
			}
		} else if ((fieldInfo != null) && (fieldInfo.length == 3)) {
			FieldCommandImpl fieldCommand = new FieldCommandImpl(fieldInfo[1],
					value, fieldInfo[2], this);
			this.commands.add(fieldCommand);
		} else {
			logger.error("Query param name [" + paramName
					+ "] is not right format.");
		}
	}

	public void addParamValue(Object value) {
		this.paramValues.add(value);
	}

	public List<Object> getParamValueList() {
		return this.paramValues;
	}

	public void addSorted(String orderBy, String ascDesc) {
		this.commands.add(new SortCommandImpl(orderBy, ascDesc, this));
	}

	public void addExample(Object object) {
		this.commands.add(new ExampleCommandImpl(object));
	}

	public List<CriteriaCommand> getCommands() {
		return this.commands;
	}

	public Set<String> getAliasSet() {
		return this.aliasSet;
	}
}
