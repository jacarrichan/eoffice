<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="net.sf.jasperreports.engine.*" %>

<%@page import="org.apache.lucene.store.jdbc.support.JdbcTemplate"%>
<%@page import="com.palmelf.core.util.AppUtil"%>
<%@page import="javax.sql.DataSource"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>以pdf格式显示报表内容</title>
</head>
<body>
<%
//初始化变量,生效日期，失效日期
//String effectiveDate = null;
//String expirationDate = null;
//effectiveDate = request.getParameter("effectiveDate");
//expirationDate = request.getParameter("expirationDate");
//if(effectiveDate==null){
	//effectiveDate="2009-01-01";
//}
//if(expirationDate==null){
	//expirationDate="2010-01-01 00:00:00";
//}
//effectiveDate就是iReport的变量$P{effectiveDate}的名称,向报表中定义的参数赋值
String t1 = null;
t1 = request.getParameter("t1");

if(t1==null){
	t1="0";
}
Map parameters = new HashMap();
parameters.put("noticeId",t1);
//parameters.put("expirationDate",expirationDate);

//数据库连接
  
  DataSource dataSource=(DataSource)AppUtil.getBean("dataSource");
  
  Connection conn=dataSource.getConnection();
  
  //读取编译后的jasper文件(用irport编译好的jasper文件)
  File exe_rpt = new File(application.getRealPath("/report/jasper/report3.jasper"));
  
  
  try{
   // 把数据填充到report上
   JasperPrint jasperPrint = JasperFillManager.fillReport(exe_rpt.getPath(),parameters,conn);
  
   // 生成pdf
   byte[] bytes = JasperRunManager.runReportToPdf(exe_rpt.getPath(),parameters,conn);
  //设置输出类型为pdf格式
   response.setContentType("application/pdf");
   response.setCharacterEncoding("UTF-8");
   request.setCharacterEncoding("UTF-8");
   
   response.setContentLength(bytes.length);
   ServletOutputStream ouputStream = response.getOutputStream();
   ouputStream.write(bytes,0,bytes.length);
   ouputStream.flush();
   ouputStream.close();
   conn.close();
   
  //清空缓存的内容
   out.clear();
   out = pageContext.pushBody();
   }catch(JRException ex){
   out.print("Jasper Output Error:"+ex.getMessage());
  }
%>
</body>
</html>