<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String boxId = request.getParameter("boxId");
	SuggestBoxService suggestBoxService = (SuggestBoxService)AppUtil.getBean("suggestBoxService");
	SuggestBox suggestBox=new SuggestBox();
	if(StringUtils.isNotEmpty(boxId)){
		suggestBox=suggestBoxService.get(new Long(boxId));
	}
	request.setAttribute("suggestBox",suggestBox);
%>
		
<%@page import="com.palmelf.eoffice.service.info.SuggestBoxService"%>
<%@page import="com.palmelf.core.util.AppUtil"%>
<%@page import="com.palmelf.eoffice.model.info.SuggestBox"%>
<%@page import="org.apache.commons.lang.StringUtils"%><table width="98%" cellpadding="0" cellspacing="1" style="padding:5px 5px 5px 5px;font:12px 宋体;color: black;line-height:24px;">
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					主　题:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font style="font-weight: bold;">
						${suggestBox.subject}
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					时　间:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="red">
						<fmt:formatDate value="${suggestBox.createtime}"pattern="yyyy-MM-dd HH:mm:ss" />
					</font>
				</td>
			</tr>
			<c:choose>
				<c:when test="${not empty suggestBox.senderFullname}">
					<tr >
						<td width="10%" style="background-color: #c7e3fa;text-align: center;">
							发表人:
						</td>
						<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
							<font color="green">
								${suggestBox.senderFullname }
							</font>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr >
						<td width="10%" style="background-color: #c7e3fa;text-align: center;">
							发表人:
						</td>
						<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
							<font color="green">
								匿名(${suggestBox.senderIp})
							</font>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
			<tr >
				<td width="100%" height="200" colspan="2" valign="top">
					&nbsp;&nbsp;${suggestBox.content}
				</td>
			</tr>
			<c:if test="${not empty suggestBox.replyContent}">
				<tr >
						<td width="10%" style="background-color: #c7e3fa;text-align: center;">
							回复人:
						</td>
						<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
							<font color="green">
								${suggestBox.replyFullname }
							</font>
						</td>
					</tr>
					<tr >
						<td width="10%" style="background-color: #c7e3fa;text-align: center;">
							回复时间:
						</td>
						<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
							<font color="green">
								${suggestBox.replyTime }
							</font>
						</td>
					</tr>
					<tr >
					<td width="100%"  colspan="2" >
						&nbsp;&nbsp;${suggestBox.replyContent}
					</td>
			</tr>
			</c:if>
	    </table>