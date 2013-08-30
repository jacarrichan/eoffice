<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>我的待办事项</title>
		<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/mobile/style.css"/>
	</head>
	 导航&nbsp;:&nbsp;<a href='<%=request.getContextPath()%>/mobile/index.do'>首页</a> &gt;&gt;我的待办事项 
	<h2>
		我的待办事项
	</h2>
	<ul>
		<c:forEach items="${taskList}" var="task">
			<li><a href="nextMobileTask.do?taskId=${task.taskId}">${task.taskName}(<fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd"/>)</a></li>
		</c:forEach>
	</ul>
</html>