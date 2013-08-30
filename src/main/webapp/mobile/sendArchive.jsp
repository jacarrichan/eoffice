<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>发文流程</title>
		<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/mobile/style.css"/>
	</head>
	 导航&nbsp;:&nbsp;<a href='index.jsp'>首页</a> &gt;&gt;我的流程
	<h2>
		发文流程
	</h2>
	<table class="info">
		<tr><td>发文分类</td></tr>
		<tr><td>
			<select name="type">
				<option>办公室公文</option>
				<option>组织部公文</option>
			</select>
		</td></tr>
		<tr>
			<td>发文标题</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="subject" class="text"/>
			</td>
		</tr>
		<tr>
			<td>发文字号</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="ziHao" class="text"/>
			</td>
		</tr>
		<tr>
			<td>发文主题</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="thread" class="text"/>
			</td>
		</tr>
		<tr>
			<td>关键词</td>
		</tr>
		<tr>
			<td><input type="text" name="keywords" class="text"/></td>
		</tr>
		<tr>
			<td>发文描述 </td>
		</tr>
		<tr>
			<td>
				<textarea rows="3" cols="10" name="" class="text"></textarea>
			</td>
		</tr>
		<tr>
			<td>正文附件</td>
		</tr>
		<tr>
			<td>
				<input type="file" name="" class="text"/>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="保存"/>
				&nbsp;
				<input type="reset" value="重置"/>
			</td>
		</tr>
	</table>
</html>