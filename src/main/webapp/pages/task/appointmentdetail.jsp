<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.palmelf.AppUtil"%>
<%@page import="com.palmelf.eoffice.service.task.AppointmentService"%>
<%@page import="com.palmelf.eoffice.service.task.impl.AppointmentServiceImpl"%>
<%@page import="com.palmelf.core.web.paging.PagingBean"%>
<%@page import="com.palmelf.eoffice.model.task.Appointment"%>
<%
	Long appointId = null;
	String strId = request.getParameter("appointId");
	String opt = request.getParameter("opt");
	String userId = request.getParameter("userId");
	AppointmentService appointmentService = (AppointmentService) AppUtil.getBean("appointmentService");
	Appointment appointment =null;
	
	if (strId != null && !"".equals(strId)) {
		appointId = new Long(strId);
	}
	
	
	if(opt != null && !"".equals(opt)){
		//使用页面的方法实现获取上一条,下一条的记录
		QueryFilter filter=new QueryFilter(request);
		filter.getPagingBean().setPageSize(1);//只取一条记录
		List<Appointment> list = null;
		filter.addFilter("Q_appUser.userId_L_EQ",userId);//只取当前用户收到的信息
		if(opt.equals("_next")){
			//点击下一条按钮,则取比当前ID大的下一条记录
			filter.addFilter("Q_appointId_L_GT", appointId.toString());
			list = appointmentService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextAppointFlag","endNext");
			}
		}else if(opt.equals("_pre")){
			//点击上一条按钮,则取比当前ID小的下一条记录
			filter.addFilter("Q_appointId_L_LT", appointId.toString());
			filter.addSorted("appointId","desc");
			list = appointmentService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextAppointFlag","endPre");
			}
		}
		if(list.size()>0){
			appointment = list.get(0);
		}else{
			appointment = appointmentService.get(appointId);
		}
	}else{
		appointment = appointmentService.get(appointId);
		request.setAttribute("__haveNextAppointFlag","");
	}
	
	request.setAttribute("appointment",appointment);
%>

<%@page import="com.palmelf.ContextUtil"%>
<%@page import="com.palmelf.core.command.QueryFilter"%><table class="table-info" width="98%" cellpadding="0" cellspacing="1">
	<tr>
		<td width="20%">主题：</td>
		<td width="80%">
			${appointment.subject}
			<input type="hidden" value="${__haveNextAppointFlag }" id="__haveNextAppointFlag"/>
			<input type="hidden" value="${appointment.appointId}" id="__curAppointId"/>
		</td>
	</tr>
	<tr>
		<td width="20%">开始时间：</td>
		<td width="80%"><c:if test="${not empty appointment.startTime}"><fmt:formatDate value="${appointment.startTime}" pattern="yyyy-MM-dd HH:mm" /></c:if></td>
	</tr>
	<tr>
		<td width="20%">结束时间：</td>
		<td width="80%"><c:if test="${not empty appointment.endTime}"><fmt:formatDate value="${appointment.endTime}" pattern="yyyy-MM-dd HH:mm" /></c:if></td>
	</tr>
	<tr>
		<td width="20%">约会内容：</td>
		<td width="80%">${appointment.content}</td>
	</tr>
	<tr>
		<td width="20%">地点：</td>
		<td width="80%">${appointment.location}</td>
	</tr>
	<tr>
		<td width="20%">备注：</td>
		<td width="80%">${appointment.notes}</td>
	</tr>
	<tr>
		<td width="20%">受邀人Email：</td>
		<td width="80%">${appointment.inviteEmails}</td>
	</tr>
</table>