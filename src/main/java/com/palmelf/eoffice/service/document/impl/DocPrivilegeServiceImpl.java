package com.palmelf.eoffice.service.document.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.document.DocPrivilegeDao;
import com.palmelf.eoffice.model.document.DocPrivilege;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.document.DocPrivilegeService;

import java.util.List;

public class DocPrivilegeServiceImpl extends BaseServiceImpl<DocPrivilege>
		implements DocPrivilegeService {
	private DocPrivilegeDao dao;

	public DocPrivilegeServiceImpl(DocPrivilegeDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<DocPrivilege> getAll(DocPrivilege docPrivilege, Long folderId,
			PagingBean pb) {
		return this.dao.getAll(docPrivilege, folderId, pb);
	}

	public List<Integer> getRightsByFolder(AppUser user, Long folderId) {
		return this.dao.getRightsByFolder(user, folderId);
	}

	public Integer getRightsByDocument(AppUser user, Long docId) {
		return this.dao.getRightsByDocument(user, docId);
	}
}
