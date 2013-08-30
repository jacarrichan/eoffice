<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.cyjt.oa.service.hrm.UserContractService"%>
<%@page import="com.cyjt.oa.model.hrm.UserContract"%>
	
<%
	String contractId = request.getParameter("contractId");
	UserContractService userContractService = (UserContractService)AppUtil.getBean("userContractService");
	UserContract userContract = userContractService.get(new Long(contractId));
	
	request.setAttribute("userContract",userContract);
%>

<h3 align="center" title="合同标题">
   <font style="font:1.9em 幼圆  ;color:blue;font-weight: bold;">
		合同信息
	</font>
	<c:choose>
	   <c:when test="${userContract.status == 1}">
			<font style="font:1.9em 幼圆  ;color:green;font-weight: bold;">
				(有效)
			</font>
		</c:when>
		<c:when test="${arch.status == 2}">
			<font style="font:1.9em 幼圆  ;color:red;font-weight: bold;">
				(终止)
			</font>
		</c:when>
	    <c:otherwise>
			<font style="font:1.9em 幼圆  ;color:green;font-weight: bold;">
				(草稿)
			</font>
		</c:otherwise>
	</c:choose>
</h3>

<span style="float:right;padding-right:12%;">
	签约日期:
	<fmt:formatDate value="${userContract.signDate}" pattern="yyyy-MM-dd"/>
</span>

<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
  <c:if test="${fn:length(userContract.contractAttachs)>0}">
	  <tr>
	  	<th width="15%" rowspan="${fn:length(userContract.contractAttachs)+1}">
	  		合同附件
	  	</th>
	  	<c:forEach var="doc" items="${userContract.contractAttachs}">
	  	 	<tr>
	  	 		<td colspan="3">
	  	 			<img src="<%=request.getContextPath()%>/images/flag/attachment.png"/>
	      			<a href="<%=request.getContextPath()%>/attachFiles/${doc.filePath}"  target="_blank">${doc.fileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
	  			</td>
	  		</tr>
	  	</c:forEach>
	  </tr>
  </c:if>
  <tr>
     <th width="15%">合同编号</th>
     <td>${userContract.contractNo}</td>
     <th width="15%">签约人</th>
     <td>${userContract.fullname}</td>
  </tr>
  <tr>
   	<th width="15%">期限形式</th>
     <td>${userContract.timeLimit}</td>
     <th width="15%">合同类型</th>
     <td>${userContract.contractType}</td>
  </tr>
  <tr>
  	 <th width="15%">生效日期</th>
     <td><fmt:formatDate value="${userContract.startDate}" pattern="yyyy-MM-dd"/></td>
     <th width="15%">是否有竞约条款</th>
     <td> 
        <c:if test="${userContract.isCompeted==1}">有</c:if>
		<c:if test="${userContract.isCompeted==0}">无</c:if>
	</td>
  </tr>
   <tr>
     <th width="15%">满约日期</th>
     <td><fmt:formatDate value="${userContract.expireDate}" pattern="yyyy-MM-dd"/></td>
     <th width="15%">是否有保密协议</th>
     <td>
        <c:if test="${userContract.isSecret==1}">有</c:if>
		<c:if test="${userContract.isSecret==0}">无</c:if>
     </td>
  </tr>
  <tr>
  	 <th width="15%">违约责任</th>
  	 <td colspan="4">${userContract.breakBurden}</td>
  </tr>
  <tr>
     <th width="15%">其他事宜</th>
     <td colspan="4">${userContract.otherItems}</td>
  </tr>
</table>

<c:if test="${fn:length(userContract.contractEvents)>0}">
	  	<c:forEach var="event" items="${userContract.contractEvents}">
	  		<table class="table-info" cellpadding="0" cellspacing="1" width="98%" align="center">
				 <tr>
				 	<th width="15%">
				 		经手人
				 	</th>
				 	<td>
				 		${event.creator }
				 	</td>
				  	<th width="15%">
				  		创建时间
				  	</th>
		  	 		<td>
		  	 			${event.createTime}
		  			</td>
		  		</tr>
		  		 <tr>
				 	<th width="15%">
				 		事件理由
				 	</th>
				 	<td colspan="3">
				 		${event.eventDescp}
				 	</td>
				 </tr>
				<tr>
				 	<th width="15%">
				 		事件名称
				 	</th>
				 	<td colspan="3">
				 		${event.eventName}
				 	</td>
				 </tr>
	  		</table>
	  	</c:forEach>
  </c:if>


