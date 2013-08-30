<%@ page pageEncoding="UTF-8"%>
<%
	//作用：用于显示附加文件的详细信息
	//作者：csx
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%">
	<tr>
		<th width="20%">
			文件名
		</th >
		<td>
		${fileAttach.fileName}
		</td>
	</tr>
	<tr>
		<th>文件路径</th>
		<td>
			${fileAttach.filePath}
		</td>
	</tr>
	<tr>
		<th>
			上传者
		</th>
		<td>
			${fileAttach.creator }
		</td>
	</tr>
	<tr>
		<th>
			备注
		</th>
		<td>
			${fileAttach.note}
		</td>
	</tr>
	<tr>
		<th>上传时间</th>
		<td>
			<fmt:formatDate value="${fileAttach.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
		<th>操作</th>
		<td>
			<img src="<%=request.getContextPath()%>/images/system/download.png"/>
			<a href="<%=request.getContextPath()%>/attachFiles/${fileAttach.filePath}" target="_blank">下载</a>
		</td>
	</tr>
</table>