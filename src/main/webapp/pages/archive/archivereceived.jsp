<%@ page pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.oa.service.archive.ArchivesHandleService"%>
<%@page import="com.cyjt.oa.model.archive.ArchivesHandle"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String handleId = request.getParameter("handleId");
	ArchivesHandleService arService = (ArchivesHandleService) AppUtil
			.getBean("archivesHandleService");
	ArchivesHandle arch = new ArchivesHandle();
	if (StringUtils.isNotEmpty(handleId)) {
		arch = arService.get(new Long(handleId));
	}
	request.setAttribute("arch", arch);
%>


<h1 align="center" title="公文标题">${arch.archives.subject}</h1>
<div><span style="font-weight: bolder">公文原件：</span> <c:forEach
	var="doc" items="${arch.archives.archivesDocs}">
	<a
		href="<%=request.getContextPath()%>/attachFiles/${doc.fileAttach.filePath}"
		target="_blank">${doc.fileAttach.fileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:forEach></div>
<span style="float: right; padding-right: 12%;">创建日期:<fmt:formatDate
	value="${arch.archives.createtime}" pattern="yyyy-MM-dd" /></span>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%"
	align="center">
	<tr>
		<th width="15%">来文文字号</th>
		<td>${arch.archives.archivesNo}</td>
		<th width="15%">来文机关</th>
		<td>${arch.archives.issueDep}</td>
		<th width="15%">文件数目</th>
		<td>${arch.archives.fileCounts}</td>
	</tr>
	<tr>
		<th width="15%">来文类型</th>
		<td>${arch.archives.archRecType.typeName}</td>
		<th width="15%">公文来源</th>
		<td>${arch.archives.sources}</td>
		<th width="15%">紧急程度</th>
		<td>${arch.archives.urgentLevel}</td>
	</tr>
	<tr>
		<th width="15%">主题词</th>
		<td colspan="3">${arch.archives.keywords}</td>
		<th width="15%">秘密等级</th>
		<td>${arch.archives.privacyLevel}</td>
	</tr>
	<tr>
		<th width="15%">内容</th>
		<td colspan="5">${arch.archives.shortContent}</td>
	</tr>
	<tr>
	    <th width="15%">我的拟办意见</th>
	    <td colspan="5">${arch.handleOpinion}</td>
	</tr>
	<!--<tr>
		<th width="15%">拟办意见</th>
		<td colspan="5">
		<ul>
			<c:forEach var="handle" items="${arch.archives.archivesHandles}">
				<c:if test="${handle.isPass==1}">
					<li>${handle.handleOpinion}&nbsp;&nbsp;同意&nbsp;&nbsp;
					${handle.userFullname}&nbsp;&nbsp; ${handle.fillTime}</li>
				</c:if>
				<c:if test="${handle.isPass==2}">
					<li>${handle.handleOpinion}&nbsp;&nbsp;不同意&nbsp;&nbsp;
					${handle.userFullname}&nbsp;&nbsp;${handle.fillTime}</li>
				</c:if>
			</c:forEach>
		</ul>
		</td>
	</tr>
--></table>

