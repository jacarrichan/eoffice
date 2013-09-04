<%@ page pageEncoding="UTF-8"
	import="com.palmelf.eoffice.service.admin.ConferenceService"%>
<%@ page
	import="com.palmelf.eoffice.model.admin.Conference,com.palmelf.eoffice.model.admin.ConfPrivilege"%>
<%@ page import="java.util.Set"%>
<%
	//作用：用于会议内容的详细信息
	//作者：YHZ
%>
<%
	ConferenceService cs = (ConferenceService) AppUtil.getBean("conferenceSerivce");
	Conference conference = cs.get(new Long(request.getParameter("confId")));
	request.setAttribute("conference", conference);
	//权限信息
	String viewers = "";
	String updaters = "";
	String summarys = "";
	Set<ConfPrivilege> set = conference.getConfPrivileges();
	for (ConfPrivilege cp : set) {
		if (cp.getRights() == 1) //查看人
			viewers += cp.getFullname() + ",";
		if (cp.getRights() == 2) //修改人
			updaters += cp.getFullname() + ",";
		if (cp.getRights() == 3) //纪要人
			summarys += cp.getFullname() + ",";
	}
	request.setAttribute("viewers", viewers.substring(0, viewers.length() - 1));
	request.setAttribute("updaters", updaters.substring(0, updaters.length() - 1));
	request.setAttribute("summarys", summarys.substring(0, summarys.length() - 1));
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="com.palmelf.core.util.AppUtil"%><table class="table-info"
	cellpadding="0" cellspacing="1" width="98%">
	<tr style="height: 25px;">
		<th colspan="4">会议信息</th>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">会议标题</th>
		<td style="width: 30%;">${ conference.confTopic }</td>
		<th style="width: 20%;">会议室名称</th>
		<td style="width: 30%;">${ conference.roomName }</td>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">会议类型</th>
		<td style="width: 30%;">${conference.confProperty }</td>
		<th style="width: 20%;">会议室</th>
		<td style="width: 30%;">${conference.roomName }</td>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">会议经费(人名币)</th>
		<td style="width: 30px;">${conference.feeBudget }</td>
		<th style="width: 20%;">地址</th>
		<td style="width: 30%;">${conference.roomLocation }</td>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">通知方式</th>
		<td style="width: 30%;"><c:if test="${conference.isEmail == 1 }">邮件</c:if> <c:if
			test="${conference.isEmail == 1 && conference.isMobile == 1}">，</c:if>
		<c:if test="${conference.isMobile == 1 }">手机短信</c:if></td>
		<th style="width: 20%;">重要级别</th>
		<c:if test="${conference.importLevel == 1}">
			<td style="width: 30%;">重要</td>
		</c:if>
		<c:if test="${conference.importLevel != 1}">
			<td style="width: 30%;">一般</td>
		</c:if>
	</tr>
	<tr style="height: 25px;">
		<th colspan="4">时间和内容信息</th>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">会议时间</th>
		<td colspan="3"><fmt:formatDate value="${conference.startTime }"
			pattern="yyyy年MM月dd日  HH:mm" /> - <fmt:formatDate
			value="${conference.endTime }" pattern="yyyy年MM月dd日  HH:mm" /></td>
	</tr>
	<tr>
		<th style="width: 20%;">会议内容</th>
		<td colspan="3" style="height: 70px;">${conference.confContent }</td>
	</tr>
	<tr style="height: 25px;">
		<th colspan="4">会议相关人员</th>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">主持人</th>
		<td style="width: 30%;">${conference.compereName }</td>
		<th style="width: 20%;">查看人</th>
		<td style="width: 30%;">${viewers }</td>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">记录人</th>
		<td style="width: 30%;">${conference.recorderName }</td>
		<th style="width: 20%;">修改人</th>
		<td style="width: 30%;">${updaters }</td>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">参加人</th>
		<td style="width: 30%;">${conference.attendUsersName }</td>
		<th style="width: 20%;">创建纪要人</th>
		<td style="width: 30%;">${summarys }</td>
	</tr>
	<tr style="height: 25px;">
		<th colspan="4">审核信息</th>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">审批人</th>
		<td colspan="3">${conference.checkName }</td>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">状态</th>
		<td colspan="3">
			<c:if test="${conference.status==3}">未通过审核</c:if>
			<c:if test="${conference.status==2}">待审核</c:if>
			<c:if test="${conference.status==1}">发送</c:if>
			<c:if test="${conference.status==0}">暂存</c:if>
		</td>
	</tr>
	<tr style="height: 40px;">
		<th style="width: 20%;">审核备注</th>
		<td colspan="3">${conference.checkReason}</td>
	</tr>
	<tr style="height: 25px;">
		<th colspan="4">附件信息</th>
	</tr>
	<tr style="height: 25px;">
		<th style="width: 20%;">操作</th>
		<td colspan="3" style="text-align: inherit;"><c:forEach
			varStatus="0" begin="0" items="${conference.attachFiles}"
			var="attachFile">
			<img src="<%=request.getContextPath()%>/images/system/download.png" />
			<a
				href="<%=request.getContextPath()%>/attachFiles/${attachFile.filePath}"
				target="_blank" title="${attachFile.filePath }">下载</a>&nbsp;
		</c:forEach></td>
	</tr>
</table>