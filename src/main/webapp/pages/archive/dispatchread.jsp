<%@ page pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.palmelf.core.util.AppUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.palmelf.eoffice.service.archive.ArchDispatchService"%>
<%@page import="com.palmelf.eoffice.model.archive.ArchDispatch"%>
<%
	String dispatchId = request.getParameter("dispatchId");
	ArchDispatchService adService = (ArchDispatchService) AppUtil
			.getBean("archDispatchService");
	ArchDispatch archDispatch = new ArchDispatch();
	if (StringUtils.isNotEmpty(dispatchId)) {
		archDispatch = adService.get(new Long(dispatchId));
	}
	request.setAttribute("archDispatch", archDispatch);
%>



<h1 align="center">${archDispatch.archives.subject}</h1>
<div><span style="font-weight: bolder">公文原件：</span> <c:forEach
	var="doc" items="${archDispatch.archives.archivesDocs}">
	<a
		href="<%=request.getContextPath()%>/attachFiles/${doc.fileAttach.filePath}"
		target="_blank">${doc.fileAttach.fileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:forEach></div>
<span style="float: right; padding-right: 12%;">创建日期:<fmt:formatDate
	value="${archDispatch.archives.createtime}" pattern="yyyy-MM-dd" /></span>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%"
	align="center">
	<tr>
		<th width="15%">来文文字号</th>
		<td>${archDispatch.archives.archivesNo}</td>
		<th width="15%">来文机关</th>
		<td>${archDispatch.archives.issueDep}</td>
		<th width="15%">文件数目</th>
		<td>${archDispatch.archives.fileCounts}</td>
	</tr>
	<tr>
		<th width="15%">来文类型</th>
		<td>${archDispatch.archives.archRecType.typeName}</td>
		<th width="15%">公文来源</th>
		<td>${archDispatch.archives.sources}</td>
		<th width="15%">紧急程度</th>
		<td>${archDispatch.archives.urgentLevel}</td>
	</tr>
	<tr>
		<th width="15%">主题词</th>
		<td colspan="3">${archDispatch.archives.keywords}</td>
		<th width="15%">秘密等级</th>
		<td>${archDispatch.archives.privacyLevel}</td>

	</tr>
	<tr>
		<th width="15%">内容</th>
		<td colspan="5">${archDispatch.archives.shortContent}</td>
	</tr>
</table>