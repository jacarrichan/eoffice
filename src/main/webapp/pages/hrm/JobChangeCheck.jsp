<%@ page pageEncoding="UTF-8"%>
<%
	//作用：用于显示附加文件的详细信息
	//作者：lyy
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%"
	align="center">
	<tr>
		<th width="70">档案编号</th>
		<td>${jobChange.profileNo} <c:if test="${jobChange.status==1}">
			<img title="通过审核"
				src="<%=basePath%>/images/flag/customer/effective.png" />
		</c:if> <c:if test="${jobChange.status==2}">
			<img title="没通过审核"
				src="<%=basePath%>/images/flag/customer/invalid.png" />
		</c:if></td>
		<th width="70">姓名</th>
		<td>${jobChange.userName}</td>
	</tr>
	<tr>
		<th width="70">原部门</th>
		<td>${jobChange.orgDepName}</td>
		<th width="70">新部门</th>
		<td>${jobChange.newDepName}</td>
	</tr>
	<tr>
		<th width="70">原职位</th>
		<td>${jobChange.orgJobName}</td>
		<th width="70">新职位</th>
		<td>${jobChange.newJobName}</td>
	</tr>
	<tr>
		<th>原薪酬标准</th>
		<td>${jobChange.orgStandardName}</td>
		<th>新薪酬标准</th>
		<td>${jobChange.newStandardName}</td>
	</tr>
	<tr>
		<th>原薪酬编号</th>
		<td>${jobChange.orgStandardNo}</td>
		<th>新薪酬编号</th>
		<td>${jobChange.newStandardNo}</td>
	</tr>
	<tr>
		<th>原薪酬总额</th>
		<td>${jobChange.orgTotalMoney}</td>
		<th>新薪酬总额</th>
		<td>${jobChange.newTotalMoney}</td>
	</tr>
	<tr>
		<th width="70">更改原由</th>
		<td colspan="3">${jobChange.changeReason}</td>
	</tr>
	<c:if test="${jobChange.status==1||jobChange.status==2}">
		<tr>
			<th>审核人</th>
			<td>${jobChange.checkName}</td>
			<th>审核时间</th>
			<td>
			<fmt:formatDate value="${jobChange.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th width="70">审核意见</th>
			<td colspan="3">${jobChange.checkOpinion}</td>
		</tr>
	</c:if>
</table>