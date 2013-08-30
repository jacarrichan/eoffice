<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="diary" items="${diaryList}">
		<tr>
			<td width="26"><c:choose>
				<c:when test="${diary.diaryType==1}">
					<img
						src="<%=request.getContextPath()%>/images/flag/system/workdiary.jpg"
						title="工作日志" />
				</c:when>
				<c:otherwise>
					<img
						src="<%=request.getContextPath()%>/images/flag/system/pdiary.png"
						title="个人日志" />
				</c:otherwise>
			</c:choose></td>
			<td><a href="#"
				onclick="eval(new DiaryDetail(${diary.diaryId}))"><c:choose><c:when test="${fn:length(diary.content)>30}">${fn:substring(diary.content,0,30)}...</c:when><c:otherwise>${diary.content}</c:otherwise></c:choose></a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${diary.dayTime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('DiaryView')">更多...</a></span></div>