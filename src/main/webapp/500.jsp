<%@page import="com.palmelf.core.util.RequestUtil" isErrorPage="true"%>
<%!private final static transient org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog("500_jsp");%>
<%
	String errorUrl = RequestUtil.getErrorUrl(request);
	//boolean isContent = (errorUrl.endsWith(".do") || errorUrl.endsWith(".jsp"));
	logger.warn("Error Occur, url: " + errorUrl+" Referrer: "+request.getHeader("REFERER"));
	logger.error(RequestUtil.getErrorInfoFromRequest(request, logger.isInfoEnabled()));
%>
<html>
	<head>
		<title>500 Page</title>
	</head>
	<body>
		<h2>Error</h2>
		<br/>
		<b>url:<%=errorUrl %></b>
	</body>
	
</html>