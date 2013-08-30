<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.oa.service.info.InMessageService"%>
<%@page import="com.cyjt.oa.model.info.InMessage"%>
<%@page import="com.cyjt.core.command.QueryFilter"%>
<%@page import="java.util.List"%>
<%@page import="com.cyjt.core.util.ContextUtil"%>
<%
		Long receiveId = null;
		String strId = request.getParameter("receiveId");
		String opt = request.getParameter("opt");
		String userId = request.getParameter("userId");
		boolean isRead=false;
		InMessageService inMessageService = (InMessageService) AppUtil.getBean("inMessageService");
		//通过id得到News
		
		InMessage inMessage = null;
		if (strId != null && !"".equals(strId)) {
			receiveId = new Long(strId);
		}
		
		
		if(opt != null && !"".equals(opt)){
			//使用页面的方法实现获取上一条,下一条的记录
			QueryFilter filter=new QueryFilter(request);
			filter.getPagingBean().setPageSize(1);//只取一条记录
			List<InMessage> list = null;
			filter.addFilter("Q_userId_L_EQ",userId);//只取当前用户收到的信息
			filter.addFilter("Q_delFlag_SN_EQ","0");//只取未被删除的信息
			if(opt.equals("_next")){
				//点击下一条按钮,则取比当前ID大的下一条记录
				filter.addFilter("Q_receiveId_L_GT", receiveId.toString());
				list = inMessageService.getAll(filter);
				if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
					request.setAttribute("__haveNextMessageFlag","endNext");
				}
			}else if(opt.equals("_pre")){
				//点击上一条按钮,则取比当前ID小的下一条记录
				filter.addFilter("Q_receiveId_L_LT", receiveId.toString());
				filter.addSorted("receiveId","desc");
				list = inMessageService.getAll(filter);
				if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
					request.setAttribute("__haveNextMessageFlag","endPre");
				}
			}
			if(list.size()>0){
				inMessage = list.get(0);
			}else{
				inMessage = inMessageService.get(receiveId);
			}
		}else{
			inMessage = inMessageService.get(receiveId);
			request.setAttribute("__haveNextMessageFlag","");
		}
		if(inMessage.getReadFlag().compareTo(InMessage.FLAG_UNREAD)==0){
			isRead=true;
			inMessage.setReadFlag(InMessage.FLAG_READ);
			inMessageService.save(inMessage);
		}
		request.setAttribute("isRead",isRead);
		request.setAttribute("inMessage",inMessage);
%>
<table  width="98%" cellpadding="0" cellspacing="1">
	<tr>
		<td >
			<input type="hidden" value="${__haveNextMessageFlag }" id="__haveNextMessageFlag"/>
			<input type="hidden" value="${inMessage.shortMessage.msgType}" id="__ReplyFlag"/>
			<input type="hidden" value="${inMessage.shortMessage.senderId}" id="__SENDID"/>
			<input type="hidden" value="${inMessage.shortMessage.sender}" id="__SENDERNAME"/>
		</td>
	</tr>
	<tr>
		<td style="padding-bottom:10px;">
			来自：
			<font color="green">
				${inMessage.shortMessage.sender}
				<input type="hidden" value="${inMessage.receiveId}" id="__curReceiveId"/>
			</font>
			&nbsp;&nbsp;
			<font color="red">
				<fmt:formatDate value="${inMessage.shortMessage.sendTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
			</font>
			&nbsp;&nbsp;
			<font color="green">
				<c:choose>
					<c:when test="${inMessage.shortMessage.msgType==1}">
						个人信息
					</c:when>
					<c:when test="${inMessage.shortMessage.msgType==2}">
						日程安排
					</c:when>
					<c:when test="${inMessage.shortMessage.msgType==3}">
						计划任务
					</c:when>
					<c:when test="${inMessage.shortMessage.msgType==4}">
						代办任务提醒
					</c:when>
					<c:when test="${inMessage.shortMessage.msgType==5}">
						系统信息
					</c:when>
					<c:otherwise>
						其他
					</c:otherwise>
				</c:choose>
			</font>
		</td>
	</tr>
	<tr>
		<td style="font:13px 宋体;color: black;line-height:24px;">
			&nbsp;&nbsp;&nbsp;&nbsp;${inMessage.shortMessage.content}
		</td>
	</tr>
	
</table>
<c:if test="${isRead}">
<script>
  var messagePanel=Ext.getCmp('MessagePanelView');
  if(messagePanel!=null){
	  messagePanel.getUpdater().update(__ctxPath+ '/info/displayInMessage.do');
  }
</script>
</c:if>