<%@ page pageEncoding="UTF-8"%>
<%
	//作用：用于显示附加文件的详细信息
	//作者：lyy
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	<tr>
		<th width="20%" nowrap="nowrap">
			标题
		</th >
		<td colspan="3">
		${hireIssue.title}
		<c:if test="${hireIssue.status==1}"><img title="通过审核" src="<%=basePath%>/images/flag/customer/effective.png"/></c:if>
			   <c:if test="${hireIssue.status==2}"><img title="没通过审核" src="<%=basePath%>/images/flag/customer/invalid.png"/></c:if>
		</td>
	</tr>
	<tr>
	    <th>招聘职位</th>
		<td>
			${hireIssue.jobName}
		</td>
		<th>招聘人数</th>
		<td>
			${hireIssue.hireCount}
		</td>
	</tr>
	<tr>
		<th width="20%">
			招聘条件
		</th >
		<td colspan="3" scope="row">
		${hireIssue.jobCondition}
		</td>
	</tr>
	<tr>
	    <th>开始时间</th>
		<td>
			<fmt:formatDate value="${hireIssue.startDate}" pattern="yyyy-MM-dd"/>
		</td>
		<th>结束时间</th>
		<td>
			<fmt:formatDate value="${hireIssue.endDate}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
	    <th>登记人</th>
		<td>
			${hireIssue.regFullname}
		</td>
		<th>登记时间</th>
		<td>
			<fmt:formatDate value="${hireIssue.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
	    <th>变更人</th>
		<td>
			${hireIssue.modifyFullname}
		</td>
		<th>变更时间</th>
		<td>
			<fmt:formatDate value="${hireIssue.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<c:if test="${hireIssue.status==1||hireIssue.status==2}">
		<tr>
		    <th>审核人</th>
			<td>
				${hireIssue.checkFullname}
			</td>
			<th>审核时间</th>
			<td>
				<fmt:formatDate value="${hireIssue.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>
				审核意见
			</th >
			<td colspan="3">
			   ${hireIssue.checkOpinion}
			</td>
	   </tr>
	</c:if>
	<tr>
		<th width="20%">
			备注
		</th >
		<td colspan="3" scope="row">
		${hireIssue.memo}
		</td>
	</tr>
</table>