<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<table width="98%" cellpadding="0" cellspacing="1" style="padding:5px 5px 5px 5px;font:12px 宋体;color: black;line-height:24px;">
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					主　题:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font style="font-weight: bold;">
						${mail.subject}
						<input type="hidden" value="${boxId}" id="__curBoxId"/>
						<input type="hidden" value="${__haveNextMailFlag}" id="__haveNextMailFlag"/>
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					时　间:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="red">
						<fmt:formatDate value="${mail.sendTime}"pattern="yyyy-MM-dd HH:mm:ss" />
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					发件人:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="green">
						${mail.appSender.fullname }
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					收件人:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="green">
						${mail.recipientNames }
					</font>
				</td>
			</tr>
			<c:if test="${not empty mail.copyToNames}">
				<tr >
					<td width="10%" style="background-color: #c7e3fa;text-align: center;">
						抄送人:
					</td>
					<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
						${mail.copyToNames }
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty mailAttachs}">
				<tr style="background-color: #c7e3fa;text-align: center;">
					<td width="10%" rowspan="${fn:length(mailAttachs)+1}">
						附　件:
					</td>
					
				</tr>
				<c:forEach items="${mailAttachs}" var="attachs">
						<tr >
							<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
								 <a href="#" onclick="FileAttachDetail.show(${attachs.fileId});" class="attachment">${attachs.fileName}</a>
							</td>
						</tr>
					</c:forEach>
			</c:if>
			<tr >
				<td width="100%"  colspan="2" >
					&nbsp;&nbsp;${mail.content}
				</td>
			</tr>
	    </table>