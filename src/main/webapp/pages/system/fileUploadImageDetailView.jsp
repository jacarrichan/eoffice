<%@ page pageEncoding="UTF-8"
	import="com.palmelf.eoffice.model.system.FileAttach,com.palmelf.eoffice.service.system.FileAttachService"%>
<%@ page import="com.palmelf.core.util.AppUtil"%>
<%@ page import="java.util.Properties,java.io.FileInputStream,java.io.InputStreamReader,java.io.Reader" %>
<!-- 
	description : 图片详细信息展示
	author : YHZ
	company : www.jee-soft.cn
	datetime : 2010-11-23AM
	 -->
<%
	String basePath = request.getContextPath();
	FileAttachService fs = (FileAttachService) AppUtil.getBean("fileAttachService");
	FileAttach f = fs.get(new Long(request.getParameter("fileId")));
	//读取Properties文件
	Properties props = new Properties();
	String filePath = session.getServletContext().getRealPath("/WEB-INF/classes");
	try {
		FileInputStream fis = new FileInputStream(filePath + "/upload-directory.properties");
		Reader r = new InputStreamReader(fis, "UTF-8");
		props.load(r);
	} catch (Exception e) {
		e.printStackTrace();
	}
	if (props.getProperty(f.getFileType()) != null)
		f.setFileType(props.getProperty(f.getFileType()));
	
	request.setAttribute("fileAttach",f);
%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table-info" cellpadding="0" cellspacing="1" width="98%"
	height="95%">
	<tr>
		<td align="center" rowspan="7" style="width: 250px; height: 250px;">
			<img alt="${fileAttach.fileName}" src="<%=basePath%>/attachFiles/${fileAttach.filePath}" width="240" height="240" align="middle" />
		</td>
		<td>名称：${fileAttach.fileName}</td>
	</tr>
	<tr>
		<td>格式：${fileAttach.ext}</td>
	</tr>
	<tr>
		<td>大小：${fileAttach.note}</td>
	</tr>
	<tr>
		<td>上传时间：<fmt:formatDate value="${fileAttach.createtime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	</tr>
	<tr>
		<td>上传者：${fileAttach.creator}</td>
	</tr>
	<tr>
		<td>文件上传模块：${fileAttach.fileType}</td>
	</tr>
	<tr><td>操作：
		<img src="<%=request.getContextPath()%>/images/system/download.png" />
			<a href="javascript:window.open(__ctxPath + '/file-downLoad?fileId='+${fileAttach.fileId});"
				target="_blank" title="${fileAttach.filePath }">下载</a>&nbsp;</td></tr>
</table>