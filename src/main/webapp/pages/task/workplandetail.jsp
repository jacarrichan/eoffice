<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<table class="table-info" width="96%" cellpadding="0" cellspacing="1">
	<tr>
		<td width="20%"><h2>计划名称：</h2></td>
		<td width="80%" colspan="4"><div class="${workPlan.icon}" style="padding-left:20px;width:auto;" title="标识">${workPlan.planName}</div></td>
	</tr>
	<tr>
		<td width="20%"><h2>计划类型：</h2></td>
		<td width="80%" colspan="4">${workPlan.planType.typeName}</td>
	</tr>
	<tr>
	    <th width="20%"><h2>时间范围:</h2></th>
		<th width="20%">开始时间:</th>
		<td width="20%"><c:if test="${not empty workPlan.startTime}"><fmt:formatDate value="${workPlan.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if></td>
		<th width="20%">结束时间：</th>
		<td width="20%"><c:if test="${not empty workPlan.endTime}"><fmt:formatDate value="${workPlan.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if></td>
	</tr>
	<tr>
		<td width="20%"><h2>内容：</h2></td>
		<td width="80%" colspan="4">${workPlan.planContent}</td>
	</tr>
	<c:if test="${not empty workPlan.issueScope}">
	  <tr>
		<td width="20%"><h2>发布范围：</h2></td>
		<td width="80%" colspan="4">${workPlan.issueScope}</td>
	</tr>
	</c:if>
	<c:if test="${not empty workPlan.participants}">
	  <tr>
		<td width="20%"><h2>参与人：</h2></td>
		<td width="80%" colspan="4">${workPlan.participants}</td>
	</tr>
	</c:if>
	<tr>
		<td width="20%"><h2>负责人：</h2></td>
		<td width="80%" colspan="4">${workPlan.principal}</td>
	</tr>
	<tr>
          <td width="20%"><h2>附件：</h2></td>
          <td width="80%" colspan="4">
             <s:iterator id="aa" value="workPlan.planFiles">
	           <a href="#" onclick="FileAttachDetail.show(${aa.fileId});" class="attachment">${aa.fileName}</a>
	        </s:iterator>          
          </td>
       </tr>
	<tr>
		<td width="20%"><h2>备注：</h2></td>
		<td width="80%" colspan="4">${workPlan.note}</td>
	</tr>
</table>