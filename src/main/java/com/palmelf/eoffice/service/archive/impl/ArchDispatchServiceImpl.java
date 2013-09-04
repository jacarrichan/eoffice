package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.archive.ArchDispatchDao;
import com.palmelf.eoffice.model.archive.ArchDispatch;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.archive.ArchDispatchService;

import java.util.List;

public class ArchDispatchServiceImpl extends BaseServiceImpl<ArchDispatch>
		implements ArchDispatchService {
	private ArchDispatchDao dao;

	public ArchDispatchServiceImpl(ArchDispatchDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ArchDispatch> findByUser(AppUser user, PagingBean pb) {
		return this.dao.findByUser(user, pb);
	}

	public int countArchDispatch(Long archivesId) {
		return this.dao.findRecordByArc(archivesId).size();
	}
}
