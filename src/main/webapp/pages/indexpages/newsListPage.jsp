<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="news" items="${newsList}">
		<tr>
			<td width="26"><c:choose>
				<c:when test="${fn:length(news.subjectIcon)>0}">
					<img
						src="<%=request.getContextPath()%>/attachFiles/${news.subjectIcon}" />
				</c:when>
				<c:otherwise>
					<img
						src="<%=request.getContextPath()%>/images/default_newsIcon.jpg" />
				</c:otherwise>
			</c:choose></td>
			<td><a href="#"
				onclick="App.MyDesktopClickTopTab('NewsDetail',${news.newsId})">${news.subject}</a></td>
			<td width="80" nowrap="nowrap"><a>${news.author}</a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${news.updateTime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('SearchNews')">更多...</a></span></div>