<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="appointment" items="${appointmentList}">
		<tr>
			<td width="26"><img
				src="<%=request.getContextPath()%>/images/flag/task/appointment.png" /></td>
			<td><a href="#"
				onclick="App.MyDesktopClickTopTab('AppointmentDetail',${appointment.appointId})">${appointment.subject}</a></td>
			<td width="220" nowrap="nowrap"><a><fmt:formatDate
				value="${appointment.startTime}" pattern="MM月dd日HH时mm分" />至<fmt:formatDate
				value="${appointment.endTime}" pattern="MM月dd日HH时mm分" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('AppointmentView')">更多...</a></span></div>