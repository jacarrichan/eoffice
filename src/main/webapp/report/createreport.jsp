<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成报表</title>
</head>
<body>
<form action="report.jsp" method="post">
请选择报表生成方式:<br>
<input type="radio" name="reportType" value="pdf" checked="checked">pdf
<input type="radio" name="reportType" value="xls">xls
<input type="radio" name="reportType" value="html">html<br>
<input type="submit" value="生成报表">
</form>
</body>
</html>