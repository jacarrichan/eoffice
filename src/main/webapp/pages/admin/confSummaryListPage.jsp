<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="cs" items="${confSummaryList}">
		<tr>
			<td width="26"><img src="<%=basePath%>/images/menus/admin/conference_confSummary.png"></img></td>
			<td><a href="#"
				onclick="eval(ConfSummaryDetailForm.show(${cs.sumId }))">${cs.confId.confTopic}</a></td>
			<td nowrap="nowrap">${cs.creator}</td>
			<td width="190" nowrap="nowrap"><a>
			<fmt:formatDate value="${cs.createtime}" pattern="MM月dd日HH时mm分" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('ConfSummaryView')">更多...</a></span></div>