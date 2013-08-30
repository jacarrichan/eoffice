package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.archive.ArchDispatchDao;
import com.cyjt.oa.model.archive.ArchDispatch;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.archive.ArchDispatchService;
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
