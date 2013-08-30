package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.ArchivesDocDao;
import com.cyjt.oa.model.archive.ArchivesDoc;
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
