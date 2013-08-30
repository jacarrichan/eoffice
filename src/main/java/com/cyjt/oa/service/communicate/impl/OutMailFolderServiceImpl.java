package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.OutMailFolderDao;
import com.cyjt.oa.model.communicate.OutMailFolder;
import com.cyjt.oa.service.communicate.OutMailFolderService;
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
