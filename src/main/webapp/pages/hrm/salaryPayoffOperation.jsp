<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cyjt.core.util.AppUtil"%>

<%@page import="com.cyjt.oa.service.hrm.SalaryPayoffService"%>
<%@page import="com.cyjt.oa.model.hrm.SalaryPayoff"%>
<%
	String recordId = request.getParameter("recordId");
	SalaryPayoffService salaryPayoffService = (SalaryPayoffService)AppUtil.getBean("salaryPayoffService");
	SalaryPayoff salaryPayoff = salaryPayoffService.get(new Long(recordId));
	request.setAttribute("salaryPayoff",salaryPayoff);
%>

<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	<tr>
		<th width="20%">
			员工档案号
		</th>
		<td>
			${salaryPayoff.profileNo }
			<c:if test="${salaryPayoff.checkStatus==1}"><img title="通过审核" src="<%=basePath%>/images/flag/customer/effective.png"/></c:if>
			<c:if test="${salaryPayoff.checkStatus==2}"><img title="没通过审核" src="<%=basePath%>/images/flag/customer/invalid.png"/></c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			员工姓名
		</th>
		<td>
			${salaryPayoff.fullname }
		</td>
	</tr>
	<tr>
		<th width="20%">
			登记人
		</th>
		<td>
			${salaryPayoff.register }
		</td>
	</tr>
	<tr>
		<th width="20%">
			登记时间
		</th>
		<td>
			<fmt:formatDate value="${salaryPayoff.regTime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核人
		</th>
		<td>
			<c:if test="${salaryPayoff.checkStatus!=0}">${salaryPayoff.checkName}</c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核时间
		</th>
		<td>
			<c:if test="${salaryPayoff.checkStatus!=0}"><fmt:formatDate value="${salaryPayoff.checkTime}" pattern="yyyy-MM-dd"/></c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核意见
		</th>
		<td>
			<c:if test="${salaryPayoff.checkStatus!=0}">${salaryPayoff.checkOpinion }</c:if>
		</td>
	</tr>
</table>