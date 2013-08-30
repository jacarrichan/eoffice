<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="message" items="${messageList}">
		<tr>
			<td width="26"><c:choose>
				<c:when test="${message.readFlag==1}">
					<img
						src="<%=request.getContextPath()%>/images/btn/info/email_open.png" />
				</c:when>
				<c:otherwise>
					<img src="<%=request.getContextPath()%>/images/btn/info/email.png" />
				</c:otherwise>
			</c:choose></td>
			<td><a href="#"
				onclick="eval(new MessageDetail(${message.receiveId}))"><c:choose><c:when test="${fn:length(message.shortMessage.content)>20}">${fn:substring(message.shortMessage.content,0,20)}...</c:when><c:otherwise>${message.shortMessage.content}</c:otherwise></c:choose></a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${message.shortMessage.sendTime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('MessageManageView')">更多...</a></span></div>