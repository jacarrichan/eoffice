<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="calendar" items="${calendarList}">
		<tr>
			<c:choose>
				<c:when test="${calendar.status==1}">
					<td width="26"><img
						src="<%=request.getContextPath()%>/images/flag/task/finish.png"
						title="完成" /></td>
					<td><a href="#"
						onclick="App.MyDesktopClickTopTab('CalendarPlanDetail',${calendar.planId})"><font
						style="text-decoration: line-through; color: red;">${calendar.content}</font></a></td>
				</c:when>
				<c:otherwise>
					<td width="26"><img
						src="<%=request.getContextPath()%>/images/flag/task/go.png"
						title="未完成" /></td>
					<td><a href="#"
						onclick="App.MyDesktopClickTopTab('CalendarPlanDetail',${calendar.planId})">${calendar.content}</a></td>
				</c:otherwise>
			</c:choose>
			<td width="220" nowrap="nowrap"><a><c:choose>
				<c:when test="${calendar.taskType==1}">
					<fmt:formatDate value="${calendar.startTime}"
						pattern="MM月dd日HH时mm分" />至<fmt:formatDate
						value="${calendar.endTime}" pattern="MM月dd日HH时mm分" />
				</c:when>
				<c:otherwise>非限期任务</c:otherwise>
			</c:choose></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('CalendarPlanView')">更多...</a></span></div>