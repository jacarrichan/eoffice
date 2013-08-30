<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cyjt.core.util.AppUtil"%>

<%@page import="com.cyjt.oa.service.hrm.EmpProfileService"%>
<%@page import="com.cyjt.oa.model.hrm.EmpProfile"%>
	
<%
	String profileId = request.getParameter("profileId");
	EmpProfileService empProfileService = (EmpProfileService)AppUtil.getBean("empProfileService");
	EmpProfile empProfile = empProfileService.get(new Long(profileId));
	request.setAttribute("empProfile",empProfile);
%>

<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	<tr>
		<td rowspan="7" width="88">
			<img title="${empProfile.fullname}" width="88" height="120" src="<%=request.getContextPath()%>/attachFiles/${empProfile.photo}">
		</td>
	</tr>
	<tr>
		<th width="20%">
			档案号
		</th>
		<td>
			${empProfile.profileNo }
			<c:if test="${empProfile.approvalStatus==1}"><img title="通过审核" src="<%=basePath%>/images/flag/customer/effective.png"/></c:if>
			<c:if test="${empProfile.approvalStatus==2}"><img title="没通过审核" src="<%=basePath%>/images/flag/customer/invalid.png"/></c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			姓名
		</th>
		<td>
			${empProfile.fullname }
		</td>
	</tr>
	<tr>
		<th width="20%">
			建档人
		</th>
		<td>
			${empProfile.creator }
		</td>
	</tr>
	<tr>
		<th width="20%">
			建档时间
		</th>
		<td>
			<fmt:formatDate value="${empProfile.createtime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核人
		</th>
		<td>
			<c:if test="${empProfile.approvalStatus!=0}">${empProfile.checkName}</c:if>
		</td>
	</tr>
	<tr>
		<th width="20%">
			审核时间
		</th>
		<td>
			<c:if test="${empProfile.approvalStatus!=0}"><fmt:formatDate value="${empProfile.checktime}" pattern="yyyy-MM-dd"/></c:if>
		</td>
	</tr>
</table>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	<tr>
		<th width="20%">
			审核意见
		</th>
		<td>
			<c:if test="${empProfile.approvalStatus!=0}">${empProfile.opprovalOpinion }</c:if>
		</td>
	</tr>
</table>