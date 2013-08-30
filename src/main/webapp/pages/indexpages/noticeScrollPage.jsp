<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="noticeDisplayDive">
	<div
		id="rollText">
		<div id="rollDiv" style="width:100%;">
		<c:forEach var="notice" items="${noticeList}">
			 <div class="noticeItem">
				<h1 align="center" class="noticeSubject">${notice.noticeTitle }</h1><br/>
				<p align="center" class="noticesubSubject"><span>发布人：${notice.postName }</span><br/><span>有效时间：<fmt:formatDate
					value="${notice.effectiveDate}" pattern="yyyy-MM-dd" />&nbsp;至&nbsp;<fmt:formatDate
					value="${notice.expirationDate }" pattern="yyyy-MM-dd" /></span></p>
				<div class="noticeContent"><p style="line-height: 20px;">${notice.noticeContent }</p></div>
			</div>
		</c:forEach>
		</div>
	</div>
</div>

<script>
    var notices=document.getElementById("rollDiv").getElementsByTagName("div");
    if(notices.length>2){
		var scrollup = new ScrollText("rollText");
		scrollup.LineHeight = 286;
		scrollup.Amount = 1;
		scrollup.Start();
    }
</script>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('SearchNotice')">更多...</a></span></div>

