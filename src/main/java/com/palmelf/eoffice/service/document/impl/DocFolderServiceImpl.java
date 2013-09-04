package com.palmelf.eoffice.service.document.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.document.DocFolderDao;
import com.palmelf.eoffice.model.document.DocFolder;
import com.palmelf.eoffice.service.document.DocFolderService;

import java.util.List;

public class DocFolderServiceImpl extends BaseServiceImpl<DocFolder> implements
		DocFolderService {
	private DocFolderDao dao;

	public DocFolderServiceImpl(DocFolderDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<DocFolder> getUserFolderByParentId(Long userId, Long parentId) {
		return this.dao.getUserFolderByParentId(userId, parentId);
	}

	public List<DocFolder> getFolderLikePath(String path) {
		return this.dao.getFolderLikePath(path);
	}

	public List<DocFolder> getPublicFolderByParentId(Long parentId) {
		return this.dao.getPublicFolderByParentId(parentId);
	}
}
