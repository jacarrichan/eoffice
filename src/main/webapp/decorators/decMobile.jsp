<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/mobile/style.css"/>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8" />
	<title>
		<decorator:title default="欢迎使用移动办公管理系统"/>
	</title>
	<decorator:head/>
</head>
<body>
	
	<decorator:body/>
</body>
</html>