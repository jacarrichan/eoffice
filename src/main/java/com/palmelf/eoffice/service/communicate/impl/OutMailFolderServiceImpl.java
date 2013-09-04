package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.communicate.OutMailFolderDao;
import com.palmelf.eoffice.model.communicate.OutMailFolder;
import com.palmelf.eoffice.service.communicate.OutMailFolderService;

import java.util.List;

public class OutMailFolderServiceImpl extends BaseServiceImpl<OutMailFolder>
		implements OutMailFolderService {
	private OutMailFolderDao dao;

	public OutMailFolderServiceImpl(OutMailFolderDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<OutMailFolder> getAllUserFolderByParentId(Long userId,
			Long parentId) {
		return this.dao.getAllUserFolderByParentId(userId, parentId);
	}

	public List<OutMailFolder> getUserFolderByParentId(Long userId,
			Long parentId) {
		return this.dao.getUserFolderByParentId(userId, parentId);
	}

	public List<OutMailFolder> getFolderLikePath(String path) {
		return this.dao.getFolderLikePath(path);
	}
}
