package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.communicate.MailFolderDao;
import com.palmelf.eoffice.model.communicate.MailFolder;
import com.palmelf.eoffice.service.communicate.MailFolderService;

import java.util.List;

public class MailFolderServiceImpl extends BaseServiceImpl<MailFolder>
		implements MailFolderService {
	private MailFolderDao dao;

	public MailFolderServiceImpl(MailFolderDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<MailFolder> getUserFolderByParentId(Long curUserId,
			Long parentId) {
		return this.dao.getUserFolderByParentId(curUserId, parentId);
	}

	public List<MailFolder> getAllUserFolderByParentId(Long userId,
			Long parentId) {
		return this.dao.getAllUserFolderByParentId(userId, parentId);
	}

	public List<MailFolder> getFolderLikePath(String path) {
		return this.dao.getFolderLikePath(path);
	}
}
