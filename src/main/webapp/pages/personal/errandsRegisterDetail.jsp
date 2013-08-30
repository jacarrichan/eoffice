<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.oa.service.personal.ErrandsRegisterService"%>
<%@page import="com.cyjt.oa.model.personal.ErrandsRegister"%>
<%
	ErrandsRegisterService erService=(ErrandsRegisterService)AppUtil.getBean("errandsRegisterService");
	String dateId=request.getParameter("dateId");
	if(StringUtils.isNotEmpty("dateId")){
		ErrandsRegister errandsRegister=erService.get(new Long(dateId));
		request.setAttribute("errandsRegister",errandsRegister);
	}
%>
<table class="table-info" width="98%" cellpadding="0" cellspacing="1">
	<tr>
		<th width="20%">请假描述</th>
		<td width="80%">
			${errandsRegister.descp}
		</td>
	</tr>
	<tr>
		<th>开始时间</th>
		<td>
			${errandsRegister.startTime}
		</td>
	</tr>
	<tr>
		<th>结束时间</th>
		<td>${errandsRegister.endTime}</td>
	</tr>
	<tr>
		<th>审批人</th>
		<td>
			${errandsRegister.approvalName}
		</td>
	</tr>
	<tr>
		<th>审批结果</th>
		<td>
			<c:choose>
				<c:when test="${errandsRegister.status==0}">
					未审批
				</c:when>
				<c:when test="${errandsRegister.status==1}">
					<font color='green'>审批通过</font>
				</c:when>
				<c:when test="${errandsRegister.status==2}">
					<font color='red'>未通过审批</font>
				</c:when>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th>审批意见</th>
		<td>${errandsRegister.approvalOption}</td>
	</tr>
</table>