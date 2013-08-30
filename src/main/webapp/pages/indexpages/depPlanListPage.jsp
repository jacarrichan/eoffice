<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="plan" items="${planList}">
		<tr>
			<td class="${plan.icon}" width="26"></td>
			<td><a href="#"
				onclick="eval(new WorkPlanDetail(${plan.planId},'${plan.planName}'))">${plan.planName}</a></td>
			<td width="220" nowrap="nowrap"><a><fmt:formatDate
				value="${plan.startTime}" pattern="MM月dd日HH时mm分" />至<fmt:formatDate
				value="${plan.endTime}" pattern="MM月dd日HH时mm分" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('DepWorkPlanView')">更多...</a></span></div>