package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.util.AppUtil;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.system.FileAttachDao;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.system.FileAttachService;

import java.io.File;
import java.util.List;

public class FileAttachServiceImpl extends BaseServiceImpl<FileAttach>
		implements FileAttachService {
	private FileAttachDao dao;

	public FileAttachServiceImpl(FileAttachDao dao) {
		super(dao);
		this.dao = dao;
	}

	public void removeByPath(String filePath) {
		FileAttach fileAttach = this.dao.getByPath(filePath);
		String fullFilePath = AppUtil.getAppAbsolutePath() + "/attachFiles/"
				+ filePath;
		this.logger.info("file:" + fullFilePath);
		File file = new File(fullFilePath);
		if (file.exists()) {
			file.delete();
		}
		if (fileAttach != null)
			this.dao.remove(fileAttach);
	}

	public FileAttach getByPath(String filePath) {
		return this.dao.getByPath(filePath);
	}

	public void mutilDel(String fileIds) {
		for (String str : fileIds.split(","))
			this.dao.remove(this.dao.get(new Long(str)));
	}

	public List<FileAttach> fileList(PagingBean pb, String filePath, boolean bo) {
		return this.dao.fileList(pb, filePath, bo);
	}

	public List<FileAttach> fileList(String fileType) {
		return this.dao.fileList(fileType);
	}
}
