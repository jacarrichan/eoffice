<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="net.sf.jasperreports.engine.*" %>

<%@page import="org.apache.lucene.store.jdbc.support.JdbcTemplate"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="javax.sql.DataSource"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>以图表的格式显示报表内容</title>
</head>
<body>
<%

  String rowid = "1";//初始化变量
//获取数据库连接
  DataSource dataSource = (DataSource)AppUtil.getBean("dataSource");
  Connection conn = dataSource.getConnection();
  
  //读取编译后的jasper文件
  File exe_rpt = new File(application.getRealPath("/report/jasper/report4.jasper"));
  
  //rowid就是iReport的变量$P{rowid}的名称,向报表中定义的参数赋值
  Map parameters = new HashMap();
  parameters.put("rowid",rowid);
  ServletOutputStream ouputStream = response.getOutputStream();
  try{
   // 把数据填充到report上
   JasperPrint jasperPrint = JasperFillManager.fillReport(exe_rpt.getPath(),parameters,conn);
  
   // 生成pdf
   byte[] bytes = JasperRunManager.runReportToPdf(exe_rpt.getPath(),parameters,conn);
  //设置输出类型为pdf格式
   response.setContentType("application/pdf");
   response.setContentLength(bytes.length);
   
   ouputStream.write(bytes,0,bytes.length);
   ouputStream.flush();
   
   conn.close();
   //清空缓存内容
   out.clear();
   out = pageContext.popBody();
   }catch(JRException ex){
   out.print("Jasper Output Error:"+ex.getMessage());
  }finally{
	  ouputStream.close();
  }

%>
</body>
</html>