package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.archive.ArchivesDao;
import com.cyjt.oa.model.archive.Archives;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.service.archive.ArchivesService;
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
