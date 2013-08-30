<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>欢迎进入移动办公OA</title>
		<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/mobile/style.css"/>
	</head>
	<body>

		<font color='green'><b>某局移动办公管理系统</b></font>
		<br/>
		欢迎您，<font color='blue'><security:authentication property="principal.fullname"/></font>，<font color='red'><a href='login.jsp'>退出</a></font>
		<hr/>
		<a href="#">首页</a>
		<ul>
			<li><a href="${ctxPath}/mobile/listMobileTask.do">待办事项</a></li>
			<li><a href="${ctxPath}/mobile/listMobileProDef.do">我的流程</a></li>
			<li><a href="#">我的约会</a></li>
			<li><a href="#">我的日程</a></li>
			<li><a href="#">我的邮件(未读[3])</a></li>
			<li><a href="#">我的短信(未读[1])</a></li>
			<li><a href="#">公告</a></li>
			<li><a href="#">我的会议</a></li>
			<li><a href="#">下属工作总结</a></li>
		</ul>
	</body>
</html>