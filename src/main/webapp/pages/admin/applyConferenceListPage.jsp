<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="ac" items="${applyConferenceList}">
		<tr>
			<td style="width: 26px;"><img
				src="<%=basePath%>/images/menus/admin/daiconfApply.png">
			</td>
			<td><a href="#"
				onclick="eval(ConferenceDetailForm.show(${ac.confId }))">${ac.confTopic}</a></td>
			<td nowrap="nowrap">${ac.checkName}</td>
			<td style="width:80px;"><a>
			<fmt:formatDate value="${ac.startTime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#" onclick="App.clickTopTab('DaiConfApplyView');">更多...</a></span></div>