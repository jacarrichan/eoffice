<%@ page pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.cyjt.oa.service.archive.LeaderReadService"%>
<%@page import="com.cyjt.oa.model.archive.LeaderRead"%>
<%
	String leaderId = request.getParameter("leaderId");
	LeaderReadService lrService = (LeaderReadService) AppUtil
			.getBean("leaderReadService");
	LeaderRead leader = new LeaderRead();
	if (StringUtils.isNotEmpty(leaderId)) {
		leader = lrService.get(new Long(leaderId));
	}
	request.setAttribute("leader", leader);
%>


<h1 align="center">${leader.archives.subject}</h1>
<div><span style="font-weight: bolder">公文原件：</span> <c:forEach
	var="doc" items="${leader.archives.archivesDocs}">
	<a
		href="<%=request.getContextPath()%>/attachFiles/${doc.fileAttach.filePath}"
		target="_blank">${doc.fileAttach.fileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:forEach></div>
<span style="float: right; padding-right: 12%;">创建日期:<fmt:formatDate
	value="${leader.archives.createtime}" pattern="yyyy-MM-dd" /></span>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%"
	align="center">

	<tr>
		<th width="15%">来文文字号</th>
		<td>${leader.archives.archivesNo}</td>
		<th width="15%">来文机关</th>
		<td>${leader.archives.issueDep}</td>
		<th width="15%">文件数目</th>
		<td>${leader.archives.fileCounts}</td>
	</tr>
	<tr>
		<th width="15%">来文类型</th>
		<td>${leader.archives.archRecType.typeName}</td>
		<th width="15%">公文来源</th>
		<td>${leader.archives.sources}</td>
		<th width="15%">紧急程度</th>
		<td>${leader.archives.urgentLevel}</td>
	</tr>
	<tr>
		<th width="15%">主题词</th>
		<td colspan="3">${leader.archives.keywords}</td>
		<th width="15%">秘密等级</th>
		<td>${leader.archives.privacyLevel}</td>
	</tr>
	<tr>
		<th width="15%">内容</th>
		<td colspan="5">${leader.archives.shortContent}</td>
	</tr>
	<tr>
	    <th width="15%">我的意见</th>
	    <td colspan="5">${leader.leaderOpinion}</td>
	</tr>
	<!--<tr>
		<th width="15%">拟办意见</th>
		<td colspan="5">
		<ul>
			<c:forEach var="handle" items="${leader.archives.archivesHandles}">
				<c:if test="${handle.isPass==1}">
					<li>${handle.handleOpinion}&nbsp;&nbsp;<font color="green">同意</font>&nbsp;&nbsp;
					${handle.userFullname}&nbsp;&nbsp; <fmt:formatDate
						value="${handle.fillTime}" pattern="yyy-MM-dd HH:mm:ss" /></li>
				</c:if>
				<c:if test="${handle.isPass==2}">
					<li>${handle.handleOpinion}&nbsp;&nbsp;<font color="red">不同意</font>&nbsp;&nbsp;
					${handle.userFullname}&nbsp;&nbsp; <fmt:formatDate
						value="${handle.fillTime}" pattern="yyy-MM-dd HH:mm:ss" /></li>
				</c:if>
			</c:forEach>
		</ul>
		</td>
	</tr>
	<tr>
		<th width="15%">领导意见</th>
		<td colspan="5">
		<ul>
			<c:forEach var="leader" items="${leader.archives.leaders}">
				<c:if test="${leader.isPass==1}">
					<li>${leader.leaderOpinion}&nbsp;&nbsp;<font color="green">同意</font>&nbsp;&nbsp;
					${leader.leaderName}&nbsp;&nbsp;<fmt:formatDate
						value="${leader.createtime}" pattern="yyy-MM-dd HH:mm:ss" /></li>
				</c:if>
				<c:if test="${leader.isPass==2}">
					<li>${leader.leaderOpinion}&nbsp;&nbsp;<font color="red">不同意</font>&nbsp;&nbsp;
					${leader.leaderName}&nbsp;&nbsp;<fmt:formatDate
						value="${leader.createtime}" pattern="yyy-MM-dd HH:mm:ss" /></li>
				</c:if>
			</c:forEach>
		</ul>
		</td>
	</tr>
--></table>
