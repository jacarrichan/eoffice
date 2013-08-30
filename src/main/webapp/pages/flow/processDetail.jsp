<%@ page pageEncoding="UTF-8"%>
<%
	//作用：用于显示附加文件的详细信息
	//作者：csx
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	<tr>
		<th width="20%">
			流程名称
		</th >
		<td>
		${proDefinition.name}
		</td>
	</tr>
	<tr>
		<th>描述</th>
		<td>
			${proDefinition.description}
		</td>
	</tr>
	<tr>
		<th>创建时间</th>
		<td>
			<fmt:formatDate value="${proDefinition.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
		<th>
			定义
		</th>
		<td>
			<c:out value="${proDefinition.defXml}" escapeXml="true"/> 
		</td>
	</tr>
</table>