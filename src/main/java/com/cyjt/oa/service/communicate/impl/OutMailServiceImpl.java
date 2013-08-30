package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.OutMailDao;
import com.cyjt.oa.model.communicate.OutMail;
import com.cyjt.oa.service.communicate.OutMailService;
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
