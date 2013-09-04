<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.palmelf.core.util.AppUtil"%>
<%@page import="com.palmelf.eoffice.service.hrm.JobChangeService"%>
<%@page import="com.palmelf.eoffice.model.hrm.JobChange"%>
<%
	String changeId = request.getParameter("changeId");
	JobChangeService jobChangeService = (JobChangeService)AppUtil.getBean("jobChangeService");
	JobChange jobChange = jobChangeService.get(new Long(changeId));
	request.setAttribute("jobChange",jobChange);
%>

<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	<tr>
		<th width="20%">
			员工档案号
		</th>
		<td>
			${jobChange.profileNo }
			<c:if test="${jobChange.status==-1}">(草稿)</c:if>
			<c:if test="${jobChange.status==0}">(未审核)</c:if>
			<c:if test="${jobChange.status==1}">(审核通过)</c:if>
			<c:if test="${jobChange.status==2}">(审核不通过)</c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			员工姓名
		</th>
		<td>
			${jobChange.userName }
		</td>
	</tr>
	
	<tr>
		<th width="20%">
			原职位
		</th>
		<td>
			${jobChange.orgJobName }
		</td>
	</tr>
	
	<tr>
		<th width="20%">
			新职位
		</th>
		<td>
			${jobChange.newJobName }
		</td>
	</tr>
	<tr>
		<th width="20%">
			登记人
		</th>
		<td>
			${jobChange.regName }
		</td>
	</tr>
	<tr>
		<th width="20%">
			登记时间
		</th>
		<td>
			<fmt:formatDate value="${jobChange.regTime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核人
		</th>
		<td>
			<c:if test="${jobChange.status!=0&&jobChange.status!=-1}">${jobChange.checkName}</c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核时间
		</th>
		<td>
			<c:if test="${jobChange.status!=0&&jobChange.status!=-1}"><fmt:formatDate value="${jobChange.checkTime}" pattern="yyyy-MM-dd"/></c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核意见
		</th>
		<td>
			<c:if test="${jobChange.status!=0&&jobChange.status!=-1}">${jobChange.checkOpinion }</c:if>
		</td>
	</tr>
</table>