package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.communicate.MailBoxDao;
import com.palmelf.eoffice.model.communicate.MailBox;
import com.palmelf.eoffice.service.communicate.MailBoxService;

import java.util.List;

public class MailBoxServiceImpl extends BaseServiceImpl<MailBox> implements
		MailBoxService {
	private MailBoxDao dao;

	public MailBoxServiceImpl(MailBoxDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Long CountByFolderId(Long folderId) {
		return this.dao.CountByFolderId(folderId);
	}

	public List<MailBox> findByFolderId(Long folderId) {
		return this.dao.findByFolderId(folderId);
	}

	public List<MailBox> findBySearch(String searchContent, PagingBean pb) {
		return this.dao.findBySearch(searchContent, pb);
	}
}
