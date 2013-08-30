package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.MailFolderDao;
import com.cyjt.oa.model.communicate.MailFolder;
import com.cyjt.oa.service.communicate.MailFolderService;
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
