<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title></title>
</head>
<body>
<table width="100%" cellpadding="0" cellspacing="1"
	style="border: 5px 5px 5px 5px;">
	<tr>
		<td align="center"
			style="font: 2.0em 宋体; color: black; font-weight: bold; padding: 10px 0px 10px 0px;">
		<s:property value="document.docName" /></td>
	</tr>
	<tr>
		<td align="center" style="padding: 0px 0px 10px 0px;">创建人:<font
			color="green"><s:property value="document.appUser.fullname" /></font>
		&nbsp;&nbsp;创建时间：<font color="red"><fmt:formatDate
			value="${document.createtime}" pattern="yyy-MM-dd HH:mm:ss" /></font>
		&nbsp;&nbsp;更新时间：<font color="red"><fmt:formatDate
			value="${document.updatetime}" pattern="yyy-MM-dd HH:mm:ss" /></font></td>
	</tr>
	<tr>
		<td style="border-top: dashed 1px #ccc;" height="28"></td>
	</tr>
	<tr>
		<td style="font: 13px 宋体; color: black; line-height: 24px;">
		   ${document.content}</td>
	</tr>
	<tr height="28"></tr>
	<tr>
		<td style="border-top: dashed 1px #ccc;" height="28"></td>
	</tr>
	<c:if test="${not empty document.attachFiles}">
	<tr>
		<td><font style="font-weight: bold;">附件:</font><s:iterator
			id="aa" value="document.attachFiles">
			<a href="#" onclick="FileAttachDetail.show(${aa.fileId});"
				class="attachment">${aa.fileName}</a>
		</s:iterator></td>
	</tr>
	</c:if>
	<tr height="28"></tr>
</table>
</body>
</html>