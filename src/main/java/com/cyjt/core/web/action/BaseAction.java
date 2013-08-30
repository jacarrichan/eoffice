package com.cyjt.core.web.action;

import com.cyjt.core.engine.MailEngine;
import com.cyjt.core.web.paging.PagingBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class BaseAction {
	public static final String SUCCESS = "success";
	public static final String INPUT = "input";
	private String successResultValue;
	public static final String JSON_SUCCESS = "{success:true}";
	protected String dir;
	protected String sort;
	protected Integer limit = Integer.valueOf(25);

	protected Integer start = Integer.valueOf(0);

	protected String jsonString = "{success:true}";
	private static final long serialVersionUID = 1L;
	protected final transient Log logger = LogFactory.getLog(getClass());
	protected MailEngine mailEngine;
	public final String CANCEL = "cancel";

	public final String VIEW = "view";

	public String getSuccessResultValue() {
		return this.successResultValue;
	}

	public void setSuccessResultValue(String successResultValue) {
		this.successResultValue = successResultValue;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getJsonString() {
		return this.jsonString;
	}

	public BaseAction() {
		setSuccessResultValue("/jsonString.jsp");
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	protected PagingBean getInitPagingBean() {
		PagingBean pb = new PagingBean(this.start.intValue(),
				this.limit.intValue());
		return pb;
	}

	public String list() {
		return "success";
	}

	public String edit() {
		return "input";
	}

	public String save() {
		return "input";
	}

	public String delete() {
		return "success";
	}

	public String multiDelete() {
		return "success";
	}

	public String multiSave() {
		return "success";
	}

	public String getDir() {
		return this.dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String execute() throws Exception {
		HttpServletRequest request = getRequest();
		String uri = request.getRequestURI();
		String url = uri.substring(request.getContextPath().length());
		url = url.replace(".do", ".jsp");
		url = "/pages" + url;

		if (this.logger.isInfoEnabled()) {
			this.logger.info("forward url:" + url);
		}
		setSuccessResultValue(url);
		return "success";
	}
}
