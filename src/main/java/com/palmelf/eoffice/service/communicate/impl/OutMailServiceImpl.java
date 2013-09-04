package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.communicate.OutMailDao;
import com.palmelf.eoffice.model.communicate.OutMail;
import com.palmelf.eoffice.service.communicate.OutMailService;

import java.util.List;
import java.util.Map;

public class OutMailServiceImpl extends BaseServiceImpl<OutMail> implements
		OutMailService {
	private OutMailDao dao;

	public OutMailServiceImpl(OutMailDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<OutMail> findByFolderId(Long folderId) {
		return this.dao.findByFolderId(folderId);
	}

	public Long CountByFolderId(Long folderId) {
		return this.dao.CountByFolderId(folderId);
	}

	public Map getUidByUserId(Long userId) {
		return this.dao.getUidByUserId(userId);
	}
}
