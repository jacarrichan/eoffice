package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.ArchivesHandleDao;
import com.cyjt.oa.model.archive.ArchivesHandle;
import java.util.List;

public class ArchivesHandleDaoImpl extends BaseDaoImpl<ArchivesHandle>
		implements ArchivesHandleDao {
	public ArchivesHandleDaoImpl() {
		super(ArchivesHandle.class);
	}

	public ArchivesHandle findByUAIds(Long userId, Long archiveId) {
		String hql = "from ArchivesHandle vo where vo.userId=? and vo.archives.archivesId=?";
		Object[] objs = { userId, archiveId };
		List list = findByHql(hql, objs);
		if (list.size() > 0) {
			return (ArchivesHandle) list.get(0);
		}
		return null;
	}

	public List<ArchivesHandle> findByAid(Long archiveId) {
		String hql = "from ArchivesHandle vo where vo.archives.archivesId=?";
		Object[] objs = { archiveId };
		return findByHql(hql, objs);
	}
}
