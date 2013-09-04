package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.ArchHastenDao;
import com.palmelf.eoffice.model.archive.ArchHasten;

import java.util.Date;
import java.util.List;

public class ArchHastenDaoImpl extends BaseDaoImpl<ArchHasten> implements
		ArchHastenDao {
	public ArchHastenDaoImpl() {
		super(ArchHasten.class);
	}

	public Date getLeastRecordByUser(Long archivesId) {
		String hql = "from ArchHasten vo where vo.archives.archivesId=? order by vo.createtime desc";
		List list = findByHql(hql, new Object[] { archivesId });
		if (list.size() > 0) {
			return ((ArchHasten) list.get(0)).getCreatetime();
		}
		return null;
	}
}
