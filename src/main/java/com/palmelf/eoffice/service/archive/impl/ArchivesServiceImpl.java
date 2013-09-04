package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.archive.ArchivesDao;
import com.palmelf.eoffice.model.archive.Archives;
import com.palmelf.eoffice.model.system.AppRole;
import com.palmelf.eoffice.service.archive.ArchivesService;

import java.util.List;
import java.util.Set;

public class ArchivesServiceImpl extends BaseServiceImpl<Archives> implements
		ArchivesService {
	private ArchivesDao dao;

	public ArchivesServiceImpl(ArchivesDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Archives> findByUserOrRole(Long userId, Set<AppRole> roles,
			PagingBean pb) {
		return this.dao.findByUserOrRole(userId, roles, pb);
	}
}
