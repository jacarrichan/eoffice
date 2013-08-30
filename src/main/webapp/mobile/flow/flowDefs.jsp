<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>我的流程</title>
		<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/mobile/style.css"/>
	</head>
	 导航&nbsp;:&nbsp;<a href='index.do'>首页</a> &gt;&gt;我的流程
	<h2>
		我的流程
	</h2>
	<ul>
		<c:forEach items="${proDefList}" var="proDef">
			<li><a href="startMobileTask.do?defId=${proDef.defId}">${proDef.name}</a></li>
		</c:forEach>
	</ul>
</html>