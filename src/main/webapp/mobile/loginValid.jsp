<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	
	if("admin".equals(username) && "1".equals(password)){
		response.sendRedirect(request.getContextPath()+"/mobile/index.jsp");
	}else{
		response.sendRedirect(request.getContextPath()+"/mobile/login.jsp?error=true");
	}
%>