<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.oa.service.task.CalendarPlanService"%>
<%@page import="com.cyjt.oa.service.task.impl.CalendarPlanServiceImpl"%>
<%@page import="com.cyjt.core.web.paging.PagingBean"%>
<%@page import="com.cyjt.oa.model.task.CalendarPlan"%>
<%@page import="com.cyjt.core.command.QueryFilter"%>
<%
	Long planId = null;
	String strId = request.getParameter("planId");
	String userId = request.getParameter("userId");
	String opt = request.getParameter("opt");
	CalendarPlanService calendarPlanService = (CalendarPlanService) AppUtil.getBean("calendarPlanService");
	CalendarPlan calendarPlan = null;
	
	if (strId != null && !"".equals(strId)) {
		planId = new Long(strId);
	}
	
	
	if(opt != null && !"".equals(opt)){
		//使用页面的方法实现获取上一条,下一条的记录
		QueryFilter filter=new QueryFilter(request);
		filter.getPagingBean().setPageSize(1);//只取一条记录
		List<CalendarPlan> list = null;
		filter.addFilter("Q_appUser.userId_L_EQ",userId);//只取当前用户收到的信息
		if(opt.equals("_next")){
			//点击下一条按钮,则取比当前ID大的下一条记录
			filter.addFilter("Q_planId_L_GT", planId.toString());
			list = calendarPlanService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextPlanFlag","endNext");
			}
		}else if(opt.equals("_pre")){
			//点击上一条按钮,则取比当前ID小的下一条记录
			filter.addFilter("Q_planId_L_LT", planId.toString());
			filter.addSorted("planId","desc");
			list = calendarPlanService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextPlanFlag","endPre");
			}
		}
		if(list.size()>0){
			calendarPlan = list.get(0);
		}else{
			calendarPlan = calendarPlanService.get(planId);
		}
	}else{
		calendarPlan = calendarPlanService.get(planId);
		request.setAttribute("__haveNextPlanFlag","");
	}
	
	request.setAttribute("calendarPlan",calendarPlan);
%>

<table class="table-info" width="98%" cellpadding="0" cellspacing="1" align="center">
	<tr>
		<th width="20%">执行人：</th>
		<td width="80%">
			${calendarPlan.fullname}
			<input type="hidden" value="${__haveNextPlanFlag}" id="__haveNextPlanFlag"/>
			<input type="hidden" value="${calendarPlan.planId}" id="__curPlanId"/>
		</td>
	</tr>
	<tr>
		<th width="20%">分配人：</th>
		<td width="80%">${calendarPlan.assignerName}</td>
	</tr>
	<tr>
		<th width="20%">内容：</th>
		<td width="80%">${calendarPlan.content}</td>
	</tr>
	<tr>
		<th width="20%">紧急程度：</th>
		<td width="80%">
			<c:choose>
				<c:when test="${calendarPlan.urgent==0}">
					一般
				</c:when>
				<c:when test="${calendarPlan.urgent==1}">
					<font color="green">重要</font>
				</c:when>
				<c:otherwise>
					<font color="red">紧急</font>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th width="20%">任务类型：</th>
		<td width="80%">
		<c:choose>
			<c:when test="${calendarPlan.taskType==1}">
					<font color="red">限期任务</font>
				</c:when>
			<c:otherwise>
					非限期任务
				</c:otherwise>
		</c:choose></td>
	</tr>
	<tr>
		<th width="20%">开始时间：</th>
		<td width="80%"><fmt:formatDate value="${calendarPlan.startTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	</tr>
	<tr>
		<th width="20%">结束时间：</th>
		<td width="80%"><fmt:formatDate value="${calendarPlan.endTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	</tr>
	<tr>
		<th width="20%">状态：</th>
		<td width="80%">
			<c:choose>
				<c:when test="${calendarPlan.status==0}">
						<font color="red">未完成</font>
				</c:when>
				<c:otherwise>
						<font color="green">完成</font>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<c:if test="${calendarPlan.status==1 }">
	<tr>
		<th>
			任务反馈
		</th>
		<td>
			${calendarPlan.feedback}
		</td>
	</tr>
	</c:if>
</table>