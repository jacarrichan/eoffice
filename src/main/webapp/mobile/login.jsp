<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录某局移动办公系统</title>
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/mobile/style.css"/>
</head>
<body>
	<%
		String error=request.getParameter("error");
		if(error!=null && !"".equals(error)){
			out.println("<font color='red'>用户名或密码错误<font><br/>");
		}
	%>
	<form action="<%=request.getContextPath()%>/mobile/signIn.do" method="post">
		<table cellspacing="1" cellpadding="0" border="0" class="info">
			<thead>
			<tr>
				<td colspan="2">欢迎登录某局移动办公系统</td>
			</tr>
			</thead>
			<tr>
				<td>用户名:</td>
				<td>
					<input name="username" type="text" value=""/>
				</td>
			</tr>
			<tr>
				<td>密码:</td>
				<td>
					<input name="password" type="password" value=""/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="登录"/>
					&nbsp;
					<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>