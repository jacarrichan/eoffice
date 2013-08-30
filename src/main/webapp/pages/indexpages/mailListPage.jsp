<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="mailBox" items="${mailBoxList}">
		<tr>
			<td width="26">
			<c:choose>
			   <c:when test="${mailBox.readFlag==0}">
			      <img
				src="<%=request.getContextPath()%>/images/flag/mail/mail.png" />
			   </c:when>
			   <c:otherwise>
			      <img
				src="<%=request.getContextPath()%>/images/flag/mail/mail_open.png" />
			   </c:otherwise>
			</c:choose>
			</td>
			<td><a href="#"
				onclick="App.MyDesktopClickTopTab('MailDetail',{mailId:${mailBox.mail.mailId},boxId:${mailBox.boxId}})">${mailBox.mail.subject}</a></td>
			<td width="80" nowrap="nowrap"><a>${mailBox.mail.sender}</a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${mailBox.sendTime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('PersonalMailBoxView')">更多...</a></span></div>