package com.palmelf.core.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportUtils {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	public ReportUtils(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public ReportUtils(HttpServletResponse response) {
		this.response = response;
	}

	public ReportUtils(HttpServletRequest request, HttpServletResponse response) {
		this(request);
		this.response = response;
	}

	public JasperPrint getJasperPrint(String filePath, Map parameter,
			JRDataSource dataSource) throws JRException {
		JasperReport jasperReport = null;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(filePath);
			return JasperFillManager.fillReport(jasperReport, parameter,
					dataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JasperPrint getPrintWithBeanList(String filePath, Map parameter,
			List list) throws JRException {
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		return getJasperPrint(filePath, parameter, dataSource);
	}

	public JRAbstractExporter getJRExporter(DocType docType) {
		JRAbstractExporter exporter = null;
		switch (docType) {
		case HTML:
			exporter = new JRPdfExporter();
			break;
		case PDF:
			exporter = new JRHtmlExporter();
			break;
		case RTF:
			exporter = new JExcelApiExporter();
			break;
		case XLS:
			exporter = new JRXmlExporter();
			break;
		case XML:
			exporter = new JRRtfExporter();
		}

		return exporter;
	}

	public void setAttrToPage(JasperPrint jasperPrint, String report_fileName,
			String report_type) {
		this.session.setAttribute("REPORT_JASPERPRINT", jasperPrint);
		this.session.setAttribute("REPORT_FILENAME", report_fileName);
		this.session.setAttribute("REPORT_TYPE", report_type);
	}

	public void complieJaxml(String jaxmlPath, String jasperPath)
			throws JRException {
		JasperCompileManager.compileReportToFile(jaxmlPath, jasperPath);
	}

	public void servletExportPDF(String jasperPath, Map params,
			List sourceList, String fileName) throws JRException, IOException,
			ServletException {
		servletExportDocument(DocType.PDF, jasperPath, params, sourceList,
				fileName);
	}

	public void servletExportHTML(String jasperPath, Map params,
			List sourceList, String imageUrl) throws JRException, IOException,
			ServletException {
		this.response.setContentType("text/html");
		this.response.setCharacterEncoding("UTF-8");
		JRAbstractExporter exporter = getJRExporter(DocType.HTML);

		JasperPrint jasperPrint = getPrintWithBeanList(jasperPath, params,
				sourceList);

		this.session.setAttribute("net.sf.jasperreports.j2ee.jasper_print",
				jasperPrint);

		PrintWriter out = this.response.getWriter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageUrl);
		exporter.exportReport();
	}

	public void servletExportExcel(String jasperPath, Map params,
			List sourceList, String fileName) throws JRException, IOException,
			ServletException {
		servletExportDocument(DocType.XLS, jasperPath, params, sourceList,
				fileName);
	}

	public void servletExportDocument(DocType docType, String jasperPath,
			Map params, List sourceList, String fileName) throws JRException,
			IOException, ServletException {
		if (docType == DocType.HTML) {
			servletExportHTML(jasperPath, params, sourceList, fileName);
			return;
		}

		JRAbstractExporter exporter = getJRExporter(docType);

		String ext = docType.toString().toLowerCase();

		if (!fileName.toLowerCase().endsWith(ext)) {
			fileName = fileName + "." + ext;
		}

		String contentType = "application/";
		if (ext.equals("xls"))
			ext = "excel";
		else if (ext.equals("xml")) {
			contentType = "text/";
		}
		contentType = contentType + ext;

		this.response.setContentType(contentType);
		this.response.setHeader(
				"Content-Disposition",
				"attachment; filename=\""
						+ URLEncoder.encode(fileName, "UTF-8") + "\"");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				getPrintWithBeanList(jasperPath, params, sourceList));

		OutputStream ouputStream = this.response.getOutputStream();

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		try {
			exporter.exportReport();
		} catch (JRException e) {
			throw new ServletException(e);
		} finally {
			if (ouputStream != null)
				try {
					ouputStream.close();
				} catch (IOException localIOException) {
				}
		}
	}

	public static enum DocType {
		PDF, HTML, XLS, XML, RTF;
	}
}
