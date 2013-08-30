package com.cyjt.oa.service.document.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.document.DocPrivilegeDao;
import com.cyjt.oa.dao.document.DocumentDao;
import com.cyjt.oa.model.document.Document;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.document.DocumentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

public class DocumentServiceImpl extends BaseServiceImpl<Document> implements
		DocumentService {
	private DocumentDao dao;

	@Resource
	private DocPrivilegeDao docPrivilegeDao;

	public DocumentServiceImpl(DocumentDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Document> findByIsShared(Document document, Date from, Date to,
			Long userId, ArrayList<Long> roleIds, Long depId, PagingBean pb) {
		return this.dao.findByIsShared(document, from, to, userId, roleIds,
				depId, pb);
	}

	public List<Document> findByPublic(String path, Document document,
			Date from, Date to, AppUser appUser, PagingBean pb) {
		return this.dao.findByPublic(path, document, from, to, appUser, pb);
	}

	public List<Document> findByPersonal(Long userId, Document document,
			Date from, Date to, String path, PagingBean pb) {
		return this.dao.findByPersonal(userId, document, from, to, path, pb);
	}

	public List<Document> findByFolder(String path) {
		return this.dao.findByFolder(path);
	}

	public List<Document> searchDocument(AppUser appUser, String content,
			PagingBean pb) {
		boolean isHaveData = false;
		Integer count = this.docPrivilegeDao.countPrivilege();
		if (count.intValue() > 0) {
			isHaveData = true;
		}
		return this.dao.searchDocument(appUser, content, isHaveData, pb);
	}
}
