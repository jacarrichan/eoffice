<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cyjt.core.util.AppUtil"%>

<%@page import="com.cyjt.oa.service.hrm.SalaryPayoffService"%>
<%@page import="com.cyjt.oa.model.hrm.SalaryPayoff"%>
<%
	String recordId = request.getParameter("recordId");
	SalaryPayoffService salaryPayoffService = (SalaryPayoffService)AppUtil.getBean("salaryPayoffService");
	SalaryPayoff salaryPayoff = salaryPayoffService.get(new Long(recordId));
	request.setAttribute("salaryPayoff",salaryPayoff);
%>

<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
	<tr>
		<th width="20%">
			发放时段
		</th>
		<td  colspan="3">
			<fmt:formatDate value="${salaryPayoff.startTime}" pattern="yyyy-MM-dd"/>
			&nbsp;&nbsp;至&nbsp;&nbsp;
			<fmt:formatDate value="${salaryPayoff.endTime}" pattern="yyyy-MM-dd"/>
			<c:if test="${salaryPayoff.checkStatus==1}"><img title="通过审核" src="<%=basePath%>/images/flag/customer/effective.png"/></c:if>
			<c:if test="${salaryPayoff.checkStatus==2}"><img title="没通过审核" src="<%=basePath%>/images/flag/customer/invalid.png"/></c:if>
		</td>
	</tr>
	<tr>
		<th>
			档案编号
		</th>
		<td  colspan="3">
			${salaryPayoff.profileNo }
		</td>
	</tr>
	<tr>
		<th>
			员工姓名
		</th>
		<td  colspan="3">
			${salaryPayoff.fullname }
		</td>
	</tr>
	<tr>
		<th>
			身份证号
		</th>
		<td  colspan="3">
			${salaryPayoff.idNo }
		</td>
	</tr>
	
	<tr>
		<th>
			薪标金额
		</th>
		<td>
			${salaryPayoff.standAmount }
		</td>
		<th>
			奖励金额
		</th>
		<td>
			${salaryPayoff.encourageAmount }
		</td>
	</tr>
	
	<tr>
		<th>
			扣除金额
		</th>
		<td>
			${salaryPayoff.deductAmount }
		</td>
		<th>
			效绩金额
		</th>
		<td>
			${salaryPayoff.achieveAmount }
		</td>
	</tr>
	
	<tr>
		<th>
			奖励描述
		</th>
		<td colspan="3">
			${salaryPayoff.encourageDesc }
		</td>
	</tr>
	<tr>
		<th>
			扣除描述
		</th>
		<td colspan="3">
			${salaryPayoff.deductDesc }
		</td>
	</tr>
	<tr>
		<th>
			备注
		</th>
		<td colspan="3">
			${salaryPayoff.memo }
		</td>
	</tr>
	<c:if test="${salaryPayoff.checkStatus==1||salaryPayoff.checkStatus==2}">
	<tr>
		<th>
			审核人
		</th>
		<td>
			${salaryPayoff.checkName }
		</td>
		<th>
			审核时间
		</th>
		<td>
			<fmt:formatDate value="${salaryPayoff.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
		<th>
			审核意见
		</th>
		<td colspan="3">
			${salaryPayoff.checkOpinion }
		</td>
	</tr>
	</c:if>
</table>
	
	
	
	