<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>请假审批流程[张大海-2010-06-10]</title>
		<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/mobile/style.css"/>
	</head>
	 导航&nbsp;:&nbsp;<a href='index.jsp'>首页</a> &gt;&gt;<a href="myTask.jsp">我的待办事项</a> 
	<h2>
		请假审批流程[张大海-2010-06-10]
	</h2>
	<form action="myTask.jsp" method="post">
		<table class="info">
			<tr>
				<th>请假人</th>
			</tr>
			<tr>
				<td>张大海</td>
			</tr>
			<tr>
				<th>请假时间</th>
			</tr>
			<tr>
				<td>2010-06-13 至 2010-06-16</td>
			</tr>
			<tr>
				<th>请假事项</th>
			</tr>
			<tr>
				<td>
					约朋友出外泉州玩三天，机会难得，请主任批准同意。
				</td>
			</tr>
			<tr>
				<th>审批意见</th>
			</tr>
			<tr>
				<td>
					<textarea rows="3" cols="12"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="同意"/>
					&nbsp;
					<input type="submit" value="不同意"/>
				</td>
			</tr>
		</table>
	</form>
</html>