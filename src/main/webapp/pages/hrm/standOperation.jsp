<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.oa.service.hrm.StandSalaryService"%>
<%@page import="com.cyjt.oa.model.hrm.StandSalary"%><table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	
<%
	String standardId = request.getParameter("standardId");
	StandSalaryService standSalaryService = (StandSalaryService)AppUtil.getBean("standSalaryService");
	StandSalary standSalary = standSalaryService.get(new Long(standardId));
	request.setAttribute("standSalary",standSalary);
%>

<tr>
		<th width="20%">
			标题
		</th >
		<td colspan="3">
				${standSalary.standardName}
				<c:if test="${standSalary.status==1}"><img title="通过审核" src="<%=basePath%>/images/flag/customer/effective.png"/></c:if>
			   <c:if test="${standSalary.status==2}"><img title="没通过审核" src="<%=basePath%>/images/flag/customer/invalid.png"/></c:if>
		</td>
	</tr>
	<tr>
	    <th>薪酬编号</th>
		<td colspan="3">
			${standSalary.standardNo}
		</td>
	</tr>
	<tr>
		<th width="20%">
			制定人
		</th >
		<td >
			${standSalary.framer}
		</td>
		<th width="20%">
			制定时间
		</th>
		<td>
			<fmt:formatDate value="${standSalary.setdownTime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<c:if test="${standSalary.status==1||standSalary.status==2}">
	<tr>
		<th width="20%">
			审核人
		</th >
		<td >
			${standSalary.checkName}
		</td>
		<th width="20%">
			审核时间
		</th>
		<td>
			<fmt:formatDate value="${standSalary.checkTime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核意见
		</th>
		<td colspan="3">
			${standSalary.checkOpinion }
		</td>
	</tr>
	</c:if>
	<tr>
		<th width="20%">
			修改人
		</th >
		<td >
			${standSalary.modifyName}
		</td>
		<th width="20%">
			修改时间
		</th>
		<td>
			<fmt:formatDate value="${standSalary.modifyTime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="20%">
			备注
		</th >
		<td colspan="3">
		${standSalary.memo}
		</td>
	</tr>
	</table>