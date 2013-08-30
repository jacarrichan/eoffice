package com.cyjt.core.web.servlet;

import com.cyjt.core.util.AppUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.FileUtil;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.FileAttach;
import com.cyjt.oa.service.system.FileAttachService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class JasperUploadServlet extends HttpServlet {
	private Log logger = LogFactory.getLog(FileUploadServlet.class);

	private ServletConfig servletConfig = null;

	private FileAttachService fileAttachService = (FileAttachService) AppUtil
			.getBean("fileAttachService");

	private String uploadPath = "";
	private String tempPath = "";

	private String fileCat = "others";

	private String filePath = "";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String extractZip = req.getParameter("extractZip");
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			factory.setSizeThreshold(4096);
			factory.setRepository(new File(this.tempPath));
			ServletFileUpload fu = new ServletFileUpload(factory);

			List<FileItem> fileItems = fu.parseRequest(req);

			for (FileItem fi : fileItems) {
				if ("file_cat".equals(fi.getFieldName())) {
					this.fileCat = fi.getString();
				}

				if ("file_path".equals(fi.getFieldName())) {
					this.filePath = fi.getString();
				}
			}

			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fileContentType = fi.getContentType();
				if (fileContentType == null) {
					continue;
				}
				if (fi.getContentType() == null) {
					continue;
				}

				String path = fi.getName();

				int start = path.lastIndexOf("\\");

				String fileName = path.substring(start + 1);

				String relativeFullPath = null;
				String generName = FileUtil.generateFilename(fileName);

				int sindex = generName.lastIndexOf("/");
				int eindex = generName.lastIndexOf(".");
				String generDir = generName.substring(sindex + 1, eindex);

				generName = generName.substring(0, sindex) + "/" + generDir
						+ "/"
						+ generName.substring(sindex + 1, generName.length());

				if (!"".equals(this.filePath))
					relativeFullPath = this.filePath;
				else {
					relativeFullPath = this.fileCat + "/" + generName;
				}

				int index = relativeFullPath.lastIndexOf("/");

				File dirPath = new File(this.uploadPath + "/"
						+ relativeFullPath.substring(0, index + 1));

				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}

				File temFile = new File(this.uploadPath + "/"
						+ relativeFullPath);
				fi.write(temFile);

				if (fileContentType.equals("application/zip")) {
					byte[] b = new byte[1024];

					ZipFile zipFile = new ZipFile(temFile);

					Enumeration enumeration = zipFile.getEntries();

					ZipEntry zipEntry = null;

					while (enumeration.hasMoreElements()) {
						zipEntry = (ZipEntry) enumeration.nextElement();

						if (zipEntry.getName().endsWith(".jasper")) {
							int indx = relativeFullPath.lastIndexOf("/");
							relativeFullPath = relativeFullPath.substring(0,
									indx);
							relativeFullPath = relativeFullPath + "/"
									+ zipEntry.getName();
						}

						File loadFile = new File(dirPath + "/"
								+ zipEntry.getName());

						if (zipEntry.isDirectory()) {
							loadFile.mkdirs();
						} else {
							if (!loadFile.getParentFile().exists()) {
								loadFile.getParentFile().mkdirs();
							}

							OutputStream outputStream = new FileOutputStream(
									loadFile);

							InputStream inputStream = zipFile
									.getInputStream(zipEntry);
							int length;
							while ((length = inputStream.read(b)) > 0) {
								outputStream.write(b, 0, length);
							}
							outputStream.close();
							inputStream.close();
						}

					}

					zipFile.close();
					temFile.delete();
				}

				FileAttach file = null;
				if (!"".equals(this.filePath)) {
					file = this.fileAttachService.getByPath(this.filePath);
				}

				if (file == null) {
					this.logger.debug("relativeFullPath=" + relativeFullPath);
					file = new FileAttach();
					file.setCreatetime(new Date());
					AppUser curUser = ContextUtil.getCurrentUser();
					if (curUser != null)
						file.setCreator(curUser.getFullname());
					else {
						file.setCreator("UNKown");
					}
					int dotIndex = fileName.lastIndexOf(".");
					file.setExt(fileName.substring(dotIndex + 1));
					file.setFileName(fileName);
					file.setFilePath(relativeFullPath);
					file.setFileType(this.fileCat);
					file.setNote(fi.getSize() + " bytes");
					file.setTotalBytes(new Double(fi.getSize()));
					this.fileAttachService.save(file);
				}

				StringBuffer sb = new StringBuffer("{success:true");
				sb.append(",fileId:")
						.append(file.getFileId())
						.append(",fileName:'")
						.append(file.getFileName())
						.append("',filePath:'")
						.append(file.getFilePath())
						.append("',message:'upload file success.("
								+ fi.getSize() + " bytes)'");
				sb.append("}");

				resp.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = resp.getWriter();
				writer.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write(
					"{'success':false,'message':'error..." + e.getMessage()
							+ "'}");
		}
	}

	public static String make8859toGB(String str) {
		try {
			String str8859 = new String(str.getBytes("8859_1"), "GB2312");
			return str8859;
		} catch (UnsupportedEncodingException ioe) {
			ioe.printStackTrace();
		}
		return str;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.servletConfig = config;
	}

	@Override
	public void init() throws ServletException {
		this.uploadPath = getServletContext().getRealPath("/attachFiles/");

		File uploadPathFile = new File(this.uploadPath);
		if (!uploadPathFile.exists()) {
			uploadPathFile.mkdirs();
		}
		this.tempPath = (this.uploadPath + "/temp");

		File tempPathFile = new File(this.tempPath);
		if (!tempPathFile.exists())
			tempPathFile.mkdirs();
	}

	public boolean saveFileToDisk(String officefileNameDisk) {
		File officeFileUpload = null;
		FileItem officeFileItem = null;

		boolean result = true;
		try {
			if ((!"".equalsIgnoreCase(officefileNameDisk))
					&& (officeFileItem != null)) {
				officeFileUpload = new File(this.uploadPath
						+ officefileNameDisk);
				officeFileItem.write(officeFileUpload);
			}
		} catch (FileNotFoundException localFileNotFoundException) {
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
