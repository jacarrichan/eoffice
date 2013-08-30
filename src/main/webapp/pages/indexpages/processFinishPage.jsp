<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="task" items="${processRunList}">
		<tr>
			<td width="26"><img
				src="<%=request.getContextPath()%>/images/menus/flow/task.png" /></td>
			<td>${task.subject}</td>
			<td width="80" nowrap="nowrap"><fmt:formatDate
				value="${task.createtime}" pattern="yyyy-MM-dd" /></td>
			<td><button type="button" title="审批明细" value=" " class="btn-flowView" 
				onclick="ProcessRunFinishView.detail('${task.runId}','${task.proDefinition.defId}','${task.piId}','${task.subject}')"></button></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('ProcessRunFinishView')">更多...</a></span></div>