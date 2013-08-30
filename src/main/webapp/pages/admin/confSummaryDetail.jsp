<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.cyjt.core.util.AppUtil"%>
<%@ page
	import="com.cyjt.oa.model.admin.ConfSummary,com.cyjt.oa.service.admin.ConfSummaryService"%>
<!-- 
	description : 会议纪要详细信息展示
	author : YHZ
	date : 2010-11-1PM
	company : www.cyjt-jee.org
 -->

<%
	String sumId = request.getParameter("sumId");
	ConfSummaryService css = (ConfSummaryService) AppUtil.getBean("confSummaryService");
	ConfSummary confSummary = css.get(new Long(sumId));
%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%"
	height="95%">
	<tr>
		<th colspan="2">会议纪要详细信息</th>
	</tr>
	<tr>
		<th style="width: 40%;">会议标题</th>
		<td style="width: 60%;">
			<a title="点击查看会议详细信息" style="text-decoration:underline; cursor:pointer;" onclick="eval(ConferenceDetailForm.show(${confSummary.confId.confId }))">
			${confSummary.confId.confTopic}</a>
		</td>
	</tr>
	<tr>
		<th style="width: 40%;">纪要创建时间</th>
		<td style="width: 60%;"><fmt:formatDate value="${confSummary.createtime}"
			pattern="yyyy年MM月dd日  HH:mm"></fmt:formatDate></td>
	</tr>
	<tr>
		<th style="width: 40%;">纪要人</th>
		<td style="width: 60%;">${confSummary.creator }</td>
	</tr>
	<tr>
		<th style="width: 40%;">纪要内容</th>
		<td style="width: 60%;">${confSummary.sumContent }</td>
	</tr>
	<tr>
		<th style="width: 40%;">状态</th>
		<td style="width: 60%;"><c:if test="${confSummary.status==1}">发送</c:if> <c:if
			test="${confSummary.status==0}">未发送</c:if></td>
	</tr>
	<tr>
		<th style="width: 40%;">附件文件</th>
		<td style="width: 60%;"><c:forEach items="${confSummary.attachFiles}"
			var="attachFile">
			<img src="<%=request.getContextPath()%>/images/system/download.png" />
			<a
				href="<%=request.getContextPath()%>/attachFiles/${attachFile.filePath}"
				target="_blank" title="${attachFile.filePath }">下载</a>&nbsp;</c:forEach></td>
	</tr>
</table>