<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.core.util.ContextUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="msthemecompatible" content="no">
		<title>意见箱</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all-notheme.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-patch.css" />
		
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
		
				<script type="text/javascript" src="<%=basePath%>/ext3/adapter/ext/ext-base.gzjs"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-all.gzjs"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-lang-zh_CN.js"></script>
	
		<script type="text/javascript" src="<%=basePath%>/js/core/AppUtil.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>

		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Toast.js"></script>
		

		<script type="text/javascript" src="<%=basePath%>/js/info/SuggestBoxView.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/SuggestBoxDisplay.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/SuggestBoxForm.js"></script>
		<script type="text/javascript">
			var curUserInfo=null;
		   Ext.onReady(function(){
			   var suggestBox = new SuggestBoxView({
						isOutSide : true
				   });


				var mySuggest = new Ext.Panel({
					border : false,
					items : [suggestBox],
					layout : 'fit'
					});
				mySuggest.render(document.body);
			   
				
			   var storeTheme=getCookie('theme');
			   	  if(storeTheme==null || storeTheme==''){
				   	  storeTheme='ext-all';
			   	  }
			      Ext.util.CSS.swapStyleSheet("theme", __ctxPath+"/ext3/resources/css/"+storeTheme+".css");  
			   
		    });
	    </script>
	</head>
	<body >

	</body>
</html>