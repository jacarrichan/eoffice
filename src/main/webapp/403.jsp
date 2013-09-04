<%@page import="com.palmelf.core.util.RequestUtil"%>
<%!private final static transient org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog("403_jsp");%>
<%
	String errorUrl = RequestUtil.getErrorUrl(request);
	//boolean isContent = (errorUrl.endsWith(".html") || errorUrl.endsWith(".jsp"));
	logger.warn("Requested url not found: " + errorUrl+" Referrer: "+request.getHeader("REFERER"));
%>
<%@ page pageEncoding="UTF-8"%><% 
	response.addHeader("__forbidden","true");
	String basePath=request.getContextPath();
%>
<html>
	<head>
		<title>访问拒绝</title>
			<style type="text/css">
			<!--
			.STYLE10 {
				font-family: "黑体";
				font-size: 36px;
			}
			-->  
			</style>
	</head>
	<body>
	 <table width="510" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
    	<td><img src="<%=basePath%>/images/error_top.jpg" width="510" height="80" /></td>
  	  </tr>
	  <tr>
	    <td height="200" align="center" valign="top" background="<%=basePath%>/images/error_bg.jpg">
	    	<table width="80%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="34%" align="right"><img src="<%=basePath%>/images/error.gif" width="128" height="128"></td>
	          <td width="66%" valign="bottom" align="center">
	          	<span class="STYLE10">访问被拒绝</span>
	          	<div style="text-align: left;line-height: 22px;">
	            <font size="2">对不起，您的当前角色没有查看此页面的权限。请联系您的系统管理员，以获得相应的权限。点击这里返回主页。如果需要技术支持，点击这里发送邮件。</font>
		        </div>
		        <a href="#" onclick="javascript:document.location.href='<%=basePath%>/j_logout.do';">重 新 登 录</a> 
		        <a href="#" onclick="javascript:history.back(-1);">后 退</a>
	     	 </td>
	      </table>
	      </td>
	  </tr>    	 
	  <tr>
    	<td><img src="<%=basePath%>/images/error_bootom.jpg" width="510" height="32" /></td>
      </tr>
	</table>
	</body>
</html>