
<%@page import="java.net.URLDecoder"%>
<%@page import="com.cyjt.oa.model.system.ReportParam"%>
<%@page import="com.cyjt.oa.service.system.ReportParamService"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.cyjt.core.command.ReportFilter"%><%@page
	language="java" contentType="text/html;charset=utf-8"
	pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.JRRuntimeException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.File"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.util.*"%>

<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page
	import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>

<%@page
	import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporter"%>
<%@page import="net.sf.jasperreports.j2ee.servlets.*"%>
<%@page import="org.apache.lucene.store.jdbc.support.JdbcTemplate"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="com.cyjt.oa.model.system.ReportTemplate"%>
<%@page import="com.cyjt.oa.service.system.ReportTemplateService"%>


<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	response.setCharacterEncoding("utf-8");
	//查询条件1、条件2
	String condition1 = null;
	//String condition2 = null;
	//报表类型
	String reportType = null;
	String strid = request.getParameter("reportId");
	Long id = new Long("0");
	if (StringUtils.isNotEmpty(strid)) {
		id = Long.parseLong(strid);
	}
	ReportParamService reportParamService = (ReportParamService) AppUtil
			.getBean("reportParamService");
	List<ReportParam> plist = reportParamService.findByRepTemp(id);
	Map map = request.getParameterMap();
	Map parameters = new HashMap();
	for (ReportParam p : plist) {
		parameters.put(p.getParamKey(), p.getDefaultVal());
	}
	Iterator it = map.entrySet().iterator();
	while (it.hasNext()) {
		Map.Entry entry = (Map.Entry) it.next();
		String key = (String) entry.getKey();
		String[] value = (String[]) entry.getValue();
		parameters.remove(key);
		parameters.put(key, URLDecoder.decode(value[0], "UTF-8"));
	}
	//根据页面传来的reportId查找上传后的报表模版路径
	String reportPath = "";
	String reportName = "";
	ReportTemplateService reportTemplateService = (ReportTemplateService) AppUtil
			.getBean("reportTemplateService");
	ReportTemplate reportTemplate = reportTemplateService.get(id);
	reportPath = reportTemplate.getReportLocation();
	reportName = reportTemplate.getTitle();
	
	//Map parameters=new ReportFilter((HttpServletRequest)request).getVariables();

	//现在报表模版只设置了一个参数，这里也暂时只接受一个参数
	//condition2 = request.getParameter("condition2");
	//if(condition2==null){
	// parameters.put("noticeId",0);
	//}else{
	//parameters.put("noticeId",condition2); 
	//}
	/*要是报表中用到了图片的话，在这里设置图片路径参数为相对路径,把图片放在项目中
	第一个参数是在iReport中设置的图片参数名字 ，第二个参数是图片所在的项目目录
	 */
	//parameters.put("REPORT_LOGO_DIR", "report\\jasper\\");
	reportType = request.getParameter("reportType");
	String rootPath = application.getRealPath("/attachFiles");//报表模版附件上传后的根目录
	
	try {
		DataSource dataSource = (DataSource) AppUtil
				.getBean("dataSource");
		Connection conn = dataSource.getConnection();
		File fullPath = new File(rootPath + "/" + reportPath);//上传后的报表模板的全路径
		//将解析完的参数传入报表模板中并生成报表
		//如果接收到的参数为pdf类型的话,则生成pdf的报表
		System.out.println(fullPath.getPath() + "," + parameters);

		if ("pdf".equals(reportType)) {
			byte[] bytes = JasperRunManager.runReportToPdf(fullPath
					.getPath(), parameters, conn);
			//设置报表生成类型为PDF
			response.setContentType("application/pdf;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentLength(bytes.length);
			reportName = reportName+".pdf";
			response.setHeader( "Content-Disposition", "attachment;filename="  + new String( reportName.getBytes("gb2312"), "ISO8859-1" ) );
			
			
			ServletOutputStream ouputStream = response
					.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			conn.close();
			out.clear();
			out = pageContext.pushBody();
		} else if ("xls".equals(reportType)) {
			//如果接受到的参数为xls(excel类型)的话,则生成xls类型的报表
			JRXlsExporter exporter = new JRXlsExporter();
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					fullPath.getPath(), parameters, conn);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					oStream);
			exporter
					.setParameter(
							JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
							Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.exportReport();
			byte[] bytes = oStream.toByteArray();
			//设置报表生成类型为excel
			response.setContentType("application/vnd.ms-excel");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentLength(bytes.length);
			reportName = reportName+".xls";
			response.setHeader( "Content-Disposition", "attachment;filename="  + new String( reportName.getBytes("gb2312"), "ISO8859-1" ) );
			
			ServletOutputStream ouputStream = response
					.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			conn.close();
			out.clear();
			out = pageContext.pushBody();
		} else {
			//否则生成html类型的报表
			JRHtmlExporter exporter = new JRHtmlExporter();
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					fullPath.getPath(), parameters, conn);
			//这里也是解决HTML报表图片不显示的
			session
					.setAttribute(
							ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
							jasperPrint);
			exporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);
			//下面这一句是解决HTML报表图片不显示的问题，注意URI得写对，这里URI是指jasper文件所在的目录
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
					"jasper?image=");
			exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporter
					.setParameter(
							JRHtmlExporterParameter.CHARACTER_ENCODING,
							"utf-8");
			exporter.setParameter(
					JRHtmlExporterParameter.OUTPUT_STREAM, oStream);
			exporter.exportReport();
			byte[] bytes = oStream.toByteArray();
			//设置报表生成类型的html
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response
					.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			conn.close();
			out.clear();
			out = pageContext.pushBody();
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>

