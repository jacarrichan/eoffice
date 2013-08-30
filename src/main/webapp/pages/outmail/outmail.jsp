<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

		<table width="98%" cellpadding="0" cellspacing="1" style="table-layout:fixed;word-break : break-all;overflow:auto;padding:5px 5px 5px 5px;font:12px 宋体;color: black;line-height:24px;">
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					主　题:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font style="font-weight: bold;">
						${outMail_view.title}
						<input type="hidden" value="${outMail_view.mailId}" id="__curOutMailId"/>
						<input type="hidden" value="${__haveNextOutMailFlag}" id="__haveNextOutMailFlag"/>
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					时　间:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="red">
						<fmt:formatDate value="${outMail_view.mailDate}"pattern="yyyy-MM-dd HH:mm:ss" />
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					发件人:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
					<font color="green">
						${outMail_view.senderName }
					</font>
				</td>
			</tr>
			<tr >
				<td width="10%" style="background-color: #c7e3fa;text-align: center;">
					收件人:
				</td>
				<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;word-wrap:break-word;word-break:break-all;">
				
					<font color="green">
						${outMail_view.receiverNames }
					</font>
				
				</td>
			</tr>
			<c:if test="${not empty outMail_view.cCNames}">
				<tr >
					<td width="10%" style="background-color: #c7e3fa;text-align: center;">
						抄送人:
					</td>
					<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
						${outMail_view.cCNames }
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty outMailFiles}">
				<tr style="background-color: #c7e3fa;text-align: center;">
					<td width="10%" rowspan="${fn:length(outMailFiles)+1}">
						附　件:
					</td>
					
				</tr>
				<c:forEach items="${outMailFiles}" var="files">
						<tr >
							<td width="90%" style="padding-left:10px;border: 1px #c7e3fa solid;">
								 <a href="#" onclick="FileAttachDetail.show(${files.fileId});" class="attachment">${files.fileName}</a>
							</td>
						</tr>
					</c:forEach>
			</c:if>
			<tr >
				<td width="100%"  colspan="2" >
					&nbsp;&nbsp;${outMail_view.content}
				</td>
			</tr>
		</table>
