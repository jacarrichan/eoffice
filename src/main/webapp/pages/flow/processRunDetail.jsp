<%@page import="java.util.List"%>
<%@page import="com.palmelf.eoffice.model.flow.ProcessRun"%>
<%@page import="com.palmelf.core.util.AppUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//显示流程的明细，（为流程的标题及内容）
	//传入piId,即流程实例ID
%>
<c:forEach items="${pfList}" var="processForm">
	<table class="table-info" cellpadding="0" cellspacing="1" width="96%">
		<tr>
			<th colspan="2">
				<div style="float:left">流程任务：[${processForm.activityName}]</div>
				<div style="float:right;width:280px">
					${processForm.creatorName} 执行于:<fmt:formatDate value="${processForm.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</th>
		</tr>
		<c:forEach items="${processForm.formDatas}" var="formData">
		<c:if test="${formData.isShowed==1}">
			<tr>
				<th width="25%">${formData.fieldLabel}</th>
				<td width="75%">
					<c:choose>
						<c:when test="${formData.fieldType=='file'}">
							<a href="<%=request.getContextPath()%>/attachFiles/${formData.strValue}" target="_blank">${formData.strValue}</a>
						</c:when>
						<c:otherwise>
							${formData.val}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:if>
		</c:forEach>
	</table>
</c:forEach>