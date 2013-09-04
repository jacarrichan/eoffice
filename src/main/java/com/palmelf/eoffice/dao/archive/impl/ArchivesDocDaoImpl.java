package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.ArchivesDocDao;
import com.palmelf.eoffice.model.archive.ArchivesDoc;

import java.util.List;

public class ArchivesDocDaoImpl extends BaseDaoImpl<ArchivesDoc> implements
		ArchivesDocDao {
	public ArchivesDocDaoImpl() {
		super(ArchivesDoc.class);
	}

	public List<ArchivesDoc> findByAid(Long archivesId) {
		String hql = "from ArchivesDoc vo where vo.archives.archivesId=?";
		Object[] objs = { archivesId };
		return findByHql(hql, objs);
	}
}
