<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="document" items="${documentList}">
		<tr>
			<td width="26"><img
				src="<%=request.getContextPath()%>/images/menus/document/personal_doc.png" /></td>
			<td><a href="#"
				onclick="App.MyDesktopClickTopTab('PublicDocumentDetail',{docId:${document.docId},docName:'${document.docName}'})">${document.docName}</a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${document.updatetime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('PersonalDocumentView')">更多...</a></span></div>