<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="task" items="${taskList}">
		<tr>
			<td width="26"><img
				src="<%=request.getContextPath()%>/images/menus/flow/task.png" /></td>
			<td><a href="#"
				onclick="App.MyDesktopClickTopTab('ProcessNextForm',{taskId:${task.taskId},activityName:'${task.activityName}'})">${task.taskName}</a></td>
			<td width="80" nowrap="nowrap">
				<c:choose>
					<c:when test="${not empty task.assignee}">${task.assignee}</c:when>
					<c:otherwise><font color='red'>暂无执行人</font></c:otherwise>
				</c:choose>
			</td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${task.createTime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('MyTaskView')">更多...</a></span></div>