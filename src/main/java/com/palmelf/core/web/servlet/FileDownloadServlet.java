package com.palmelf.core.web.servlet;

import com.palmelf.core.util.AppUtil;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.system.FileAttachService;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileAttachService fileAttachService = (FileAttachService) AppUtil
			.getBean("fileAttachService");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fileId = req.getParameter("fileId");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (StringUtils.isNotEmpty(fileId)) {
			FileAttach fileAttach = this.fileAttachService
					.get(new Long(fileId));
			String ext = fileAttach.getExt();

			if (ext.toLowerCase().endsWith("zip"))
				resp.setContentType("application/x-zip-compressed");
			else if (ext.toLowerCase().endsWith("rar"))
				resp.setContentType("application/octet-stream");
			else if (ext.toLowerCase().endsWith("doc"))
				resp.setContentType("application/msword");
			else if ((ext.toLowerCase().endsWith("xls"))
					|| (ext.toLowerCase().endsWith("csv"))) {
				resp.setContentType("application/ms-excel ");
			} else if (ext.toLowerCase().endsWith("pdf"))
				resp.setContentType("application/pdf");
			else {
				resp.setContentType("application/x-msdownload");
			}

			ServletOutputStream out = null;
			try {
				FileInputStream fileIn = new FileInputStream(
						getServletContext().getRealPath("/") + "/attachFiles/"
								+ fileAttach.getFilePath());

				resp.setHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(fileAttach.getFileName(), "UTF-8"));

				out = resp.getOutputStream();

				byte[] buff = new byte[1024];
				int leng = fileIn.read(buff);
				while (leng > 0) {
					out.write(buff, 0, leng);
					leng = fileIn.read(buff);
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				if (out != null) {
					try {
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} finally {
				if (out != null) {
					try {
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
