<%@page import="com.cyjt.core.util.RequestUtil"%>
<%!private final static transient org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog("404_jsp");%>
<%
	String errorUrl = RequestUtil.getErrorUrl(request);
	//boolean isContent = (errorUrl.endsWith(".html") || errorUrl.endsWith(".jsp"));
	logger.warn("Requested url not found: " + errorUrl+" Referrer: "+request.getHeader("REFERER"));

%>
<html>
	<head>
		<title>404 Page</title>
	</head>
	<body>
		<h2>Page not found </h2>
		<br/>
		<b>url:<%=errorUrl %></b>
	</body>
	
</html>