<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.palmelf.core.util.AppUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.palmelf.eoffice.service.admin.RegulationService"%>
<%@page import="com.palmelf.eoffice.model.admin.Regulation"%>
<%
	String regId = request.getParameter("regId");
	RegulationService regulationService = (RegulationService)AppUtil.getBean("regulationService");
	Regulation regulation=new Regulation();
	if(StringUtils.isNotEmpty(regId)){
		regulation=regulationService.get(new Long(regId));
	}
	request.setAttribute("regulation",regulation);
%>
<table width="98%" cellpadding="0" cellspacing="1" style="padding:5px 5px 5px 5px;font:12px 宋体;color: black;line-height:24px;">
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					主　题:
				</td>
				<td colspan="3" width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font style="font-weight: bold;font-size: 16px;">
						${regulation.subject}
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					关键字:
				</td>
				<td width="40%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					${regulation.keywords}
				</td>
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					时　间:
				</td>
				<td width="40%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="red">
						<fmt:formatDate value="${regulation.issueDate}"pattern="yyyy-MM-dd HH:mm:ss" />
					</font>
				</td>
			</tr>
			
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					发布人:
				</td>
				<td width="40%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="">
						${regulation.issueFullname}
					</font>
				</td>
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					发布部门:
				</td>
				<td width="40%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="">
						${regulation.issueDep}
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					类　型:
				</td>
				<td colspan="3" width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					${regulation.globalType.typeName}
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					范　围:
				</td>
				<td colspan="3" width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="green">
						${regulation.recDeps}
					</font>
				</td>
			</tr>
			
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					接收人:
				</td>
				<td colspan="3" width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="green">
						${regulation.recUsers}
					</font>
				</td>
			</tr>
			<c:if test="${fn:length(regulation.regAttachs)>0}">
				  <tr>
				  	<th width="15%" rowspan="${fn:length(regulation.regAttachs)+1}" style="background-color: #c7e3fa;text-align: center;">
				  		附　件
				  	</th>
				  	<c:forEach var="regfile" items="${regulation.regAttachs}">
				  	 	<tr>
				  	 		<td colspan="3" style="padding-left:10px;border: 1px #c7e3fa solid;">
				  	 			<img src="<%=request.getContextPath()%>/images/flag/attachment.png"/>
				      			<a href="<%=request.getContextPath()%>/attachFiles/${regfile.filePath}"  target="_blank">${regfile.fileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				  			</td>
				  		</tr>
				  	</c:forEach>
				  </tr>
			  </c:if>
			  
			<tr >
				<td width="100%" height="200" colspan="4" valign="top">
					&nbsp;&nbsp;${regulation.content}
				</td>
			</tr>
	    </table>